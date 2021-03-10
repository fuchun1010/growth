package com.tank.stream.udf.demo;

import com.tank.stream.pojo.Word;
import com.tank.stream.udf.UdfForStrLength;
import io.vavr.control.Try;
import lombok.*;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import java.time.Duration;

/**
 * @author tank198435163.com
 */
public class WordForUdf {

  @SneakyThrows
  public void statistics(@NonNull final String... words) {
    val env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.setParallelism(1);
    val settings = EnvironmentSettings.newInstance().useBlinkPlanner().build();
    val tableEnvironment = StreamTableEnvironment.create(env, settings);
    val wordsStream = env.fromElements(words).map((MapFunction<String, Word>) value -> new Word(value, 1)).returns(Types.POJO(Word.class));

    val config = tableEnvironment.getConfig();
    config.setIdleStateRetention(Duration.ofHours(20));

    tableEnvironment.createTemporaryFunction("strLen", UdfForStrLength.class);
    tableEnvironment.createTemporaryView("word", wordsStream);

    val sql = "select str, count(str) as cnt from word group by str";

    Table queryResult = tableEnvironment.sqlQuery(sql);

    tableEnvironment.toRetractStream(queryResult, Row.class)
            .map((MapFunction<Tuple2<Boolean, Row>, Tuple2<Boolean, Word>>) value -> {
              val word = new Word();
              val cnt = Try.of(() -> value.f1.getField(0)).map(Object::toString).map(Integer::parseInt).getOrElse(0);
              val str = Try.of(() -> value.f1.getField(1)).map(Object::toString).getOrElse("-");
              word.setCnt(cnt);
              word.setStr(str);
              return new Tuple2<>(value.f0, word);
            })
            .returns(Types.TUPLE(Types.BOOLEAN, Types.POJO(Word.class)))
            .filter(result -> !"-".equals(result.f1.getStr()))
            .print();

    env.execute("WordForUdf");

  }

  /**
   * @author tank198435163.com
   */
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  private static class TableResult {

    private int len;

    private int cnt;
  }

}
