package com.tank.stream;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;

import java.util.Objects;

/**
 * @author tank198435163.com
 */
public class WordCounter {

  public WordCounter(@NonNull final String... words) {
    this.words = words;
  }

  @SneakyThrows({Exception.class})
  public void count() {
    Objects.requireNonNull(this.words, "words not allowed empty");
    val env = ExecutionEnvironment.getExecutionEnvironment();
    env.setParallelism(1);

    env.fromElements(this.words)
            .name("bach-word-counter")
            .filter(Objects::nonNull)
            .map((MapFunction<String, Tuple2<String, Integer>>) value -> new Tuple2<>(value, 1))
            .returns(TypeInformation.of(new Tuple2TypeHint()))
            .groupBy(0)
            .sum(1)
            .print();

  }

  private static class Tuple2TypeHint extends TypeHint<Tuple2<String, Integer>> {

  }

  private final String[] words;

}
