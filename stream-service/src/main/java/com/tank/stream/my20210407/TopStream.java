package com.tank.stream.my20210407;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.SlidingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.sql.Timestamp;
import java.time.Duration;

import static java.lang.Long.parseLong;

/**
 * @author tank198435163.com
 */
public class TopStream {

  @SneakyThrows
  public void process() {
    val env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
    env.getCheckpointConfig().setCheckpointTimeout(60000);
    env.getCheckpointConfig().setCheckpointInterval(12000);
    env.setParallelism(1);

    val resource = TopStream.class.getClassLoader().getResource("user-behaver.csv");

    Preconditions.checkArgument(resource != null, "resource not exists");

    val behaveStream = env.readTextFile(resource.getPath(), "UTF-8")
            .filter(StrUtil::isNotBlank)
            .map((MapFunction<String, UserBehave>) line -> {
              val fields = line.split(",");
              return new UserBehave(parseLong(fields[0]), parseLong(fields[1]), parseLong(fields[2]), fields[3], parseLong(fields[4]));
            })
            .returns(Types.POJO(UserBehave.class))
            .uid("userBehave")
            .filter((FilterFunction<UserBehave>) value -> "pv".equalsIgnoreCase(value.getOp()))
            .uid("pv");


    val allWindow = new WindowFunction<Long, ItemViewCount, Long, TimeWindow>() {

      @Override
      public void apply(Long itemId, TimeWindow window, Iterable<Long> input, Collector<ItemViewCount> out) throws Exception {
        val viewCount = new ItemViewCount().setItemId(itemId).setTimestamp(window.getEnd()).setCounter(input.iterator().next());
        out.collect(viewCount);
      }
    };

    val streamOperator = behaveStream.assignTimestampsAndWatermarks(
            WatermarkStrategy.<UserBehave>forBoundedOutOfOrderness(Duration.ofSeconds(5))
                    .withTimestampAssigner((a, b) -> a.getTimestamp() * 1000)
    );

    DataStream<ItemViewCount> itemViewCountDataStream = streamOperator
            .keyBy((KeySelector<UserBehave, Long>) UserBehave::getItemId)
            .window(SlidingEventTimeWindows.of(Time.hours(1), Time.minutes(5)))
            .aggregate(new ItemCountAgg(), allWindow);


    DataStream<String> result = itemViewCountDataStream
            .keyBy("timestamp")
            .process(new Top(5))
            .uid("window_end");

    result.print("only print");


    env.execute("topN");
  }

  private static class ItemCountAgg implements AggregateFunction<UserBehave, Long, Long> {

    @Override
    public Long createAccumulator() {
      return 0L;
    }

    @Override
    public Long add(UserBehave value, Long accumulator) {
      return accumulator + 1;
    }

    @Override
    public Long getResult(Long accumulator) {
      return accumulator;
    }

    @Override
    public Long merge(Long a, Long b) {
      return a + b;
    }
  }


  private static class Top extends KeyedProcessFunction<Tuple, ItemViewCount, String> {

    public Top(@NonNull final Integer n) {
      this.n = n;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
      super.open(parameters);
      this.itemViewCountListState = super.getRuntimeContext().getListState(new ListStateDescriptor<ItemViewCount>("itemViewCountList", ItemViewCount.class));
    }

    @Override
    public void processElement(ItemViewCount value, Context ctx, Collector<String> out) throws Exception {
      this.itemViewCountListState.add(value);
      ctx.timerService().registerEventTimeTimer(value.getTimestamp() + 1);
    }

    @Override
    public void onTimer(long timestamp, OnTimerContext ctx, Collector<String> out) throws Exception {
      val itemViewCounts = Lists.newArrayList(this.itemViewCountListState.get());
      itemViewCounts.sort((o1, o2) -> o2.getCounter().compareTo(o1.getCounter()));


      val builder = new StringBuilder();
      builder.append("窗口结束时间:").append(new Timestamp(timestamp - 1).toString()).append("\n");

      for (int i = 0; i < Math.min(this.n, itemViewCounts.size()); i++) {
        val viewCount = itemViewCounts.get(i);
        builder.append(viewCount.toString());
        builder.append("\n");
      }


      out.collect(builder.toString());
    }

    private ListState<ItemViewCount> itemViewCountListState;

    private Integer n;
  }
}
