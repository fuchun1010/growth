package com.tank.stream.my20210409;

import lombok.*;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.MapState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.util.Optional;

/**
 * note implements
 * stream style for word statistic
 *
 * @author tank198435163.com
 */
public class WordStatisticStream implements WordStatistic {

  @Override
  @SneakyThrows
  public void statistics() {
    val environment = StreamExecutionEnvironment.getExecutionEnvironment();
    environment.setParallelism(1);
    val path = WordStatisticStream.class.getClassLoader().getResource("word.txt").getPath();

    val stream = environment.readTextFile(path, "utf-8".toUpperCase())
            .map((MapFunction<String, Word>) value -> new Word(value, 1))
            .returns(Types.POJO(Word.class))
            .keyBy((KeySelector<Word, String>) value -> value.str);


    stream.process(new KeyedProcessFunction<String, Word, Word>() {
      @Override
      public void processElement(Word value, Context ctx, Collector<Word> out) throws Exception {
        var counter = Optional.ofNullable(this.mapState.get(value.getStr())).orElse(0);
        counter++;
        this.mapState.put(value.getStr(), counter);
        val word = new Word(value.getStr(), counter);
        out.collect(word);
      }

      @Override
      public void open(Configuration parameters) throws Exception {
        this.mapState = this.getRuntimeContext().getMapState(new MapStateDescriptor<String, Integer>("word", String.class, Integer.class));
      }

      private MapState<String, Integer> mapState;

    }).print();


    environment.execute("word-stream-statistics");
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Word {

    @Override
    public String toString() {
      return "Word{" +
              "str='" + str + '\'' +
              ", counter=" + counter +
              '}';
    }

    private String str;

    private int counter;
  }
}
