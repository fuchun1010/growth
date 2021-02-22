package com.tank.stream;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.flink.api.common.ExecutionMode;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;

import java.util.Objects;

/**
 * @author tank198435163.com
 */
public class WordCounter {

  public WordCounter() {

  }

  public WordCounter(@NonNull final String... words) {
    this.words = words;
  }

  @SneakyThrows({Exception.class})
  public void count() {
    if (Objects.isNull(this.words)) {
      this.words = new String[]{"hello", "hello"};
    }
    Objects.requireNonNull(this.words, "words not allowed empty");
    val env = ExecutionEnvironment.getExecutionEnvironment();
    env.setParallelism(1);
    env.getConfig().setExecutionMode(ExecutionMode.BATCH);
    env.fromElements(this.words)
            .name("bach-word-counter")
            .filter(Objects::nonNull)
            .map((MapFunction<String, Tuple2<String, Integer>>) value -> new Tuple2<>(value, 1))
            .returns(Types.TUPLE(Types.STRING, Types.INT))
            .groupBy(0)
            .sum(1)
            .print();

  }


  private String[] words;

}
