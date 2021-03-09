package com.tank.stream.customer;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import java.io.Serializable;

import static org.apache.flink.table.api.Expressions.$;

/**
 * @author tank198435163.com
 */
public class TableWrapper {

  @SneakyThrows({Exception.class})
  public void run() {
    val env = StreamExecutionEnvironment.getExecutionEnvironment();
    val settings = EnvironmentSettings.newInstance().useBlinkPlanner().build();
    val tableEnv = StreamTableEnvironment.create(env, settings);

    env.setParallelism(1);
    val stream = env.<String>fromElements("hello", "jack", "hello", "hello", "lucy");

    val wordsTable = tableEnv.fromDataStream(stream, $("word"));


    val groupBy = wordsTable
            .groupBy($("word"))
            .select(
                    $("word"),
                    $("word").count().as("counter")
            );


    tableEnv.toRetractStream(groupBy, Row.class).map(new MapFunction<Tuple2<Boolean, Row>, Word>() {
      @Override
      public Word map(Tuple2<Boolean, Row> value) throws Exception {
        val word = new Word();
        val str = value.f1.getField(0).toString();
        val counter = Long.parseLong(value.f1.getField(1).toString());
        if (!value.f0) {
          word.deleted = 1;
        }
        word.setCounter(counter);
        word.setStr(str);
        return word;
      }
    }).print();


    env.execute("tableWrapper");
  }

  @Getter
  @Setter
  private static class Word implements Serializable {

    private int deleted = 0;

    private String str = null;

    private Long counter = 0L;

    @Override
    public String toString() {
      return "Word{" +
              "deleted=" + deleted +
              ", str='" + str + '\'' +
              ", counter=" + counter +
              '}';
    }
  }

}
