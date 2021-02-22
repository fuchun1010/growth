package com.tank.stream;

import com.google.common.base.Preconditions;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author tank198435163.com
 */
public class WordCounterV2 {

  public WordCounterV2() {
    this.words = new String[]{"hello", "hello"};
  }

  public WordCounterV2(@NonNull final String... words) {
    Preconditions.checkArgument(words.length != 0, "words not allowed empty");
    this.words = words;
  }


  @SneakyThrows({Exception.class})
  public void count() {
    val env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.getConfig().setAutoWatermarkInterval(100);
    env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
    env.fromElements(this.words)
            .name("word")
            .map((MapFunction<String, Tuple2<String, Integer>>) value -> new Tuple2<>(value, 1))
            .name("transform")
            .returns(Types.TUPLE(Types.STRING, Types.INT))
            .name("generic")
            .keyBy((KeySelector<Tuple2<String, Integer>, String>) value -> value.f0)
            .sum(1)
            .name("aggregate")
            .print();

    env.execute("wordCounter v2");
  }

  private final String[] words;

}
