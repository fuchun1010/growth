package com.tank.stream.spike;

import cn.hutool.core.util.StrUtil;
import com.tank.stream.pojo.Sensor;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author tank198435163.com
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SensorStreamTest {

  @Test
  @DisplayName("温度计测试")
  @SneakyThrows
  void testStream() {
    val environment = StreamExecutionEnvironment.getExecutionEnvironment();

    environment.setParallelism(1);
    environment.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
    environment.getCheckpointConfig().setCheckpointTimeout(60000);

    environment.addSource(new RandomSensorSource())
            .map((MapFunction<Sensor, Sensor>) value -> {
              double a = value.getTemperature().doubleValue();
              value.setTemperature(a < 0 ? BigDecimal.valueOf(Math.abs(a)) : value.getTemperature());
              return value;
            })
            .returns(Types.POJO(Sensor.class))
            .assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<Sensor>(Time.seconds(5)) {
              @Override
              public long extractTimestamp(Sensor element) {
                return 0;
              }
            })
            .keyBy(Sensor::getId)
            .print("sensor");


    environment.execute(this.getClass().getSimpleName());
  }


  private static class RandomSensorSource implements SourceFunction<Sensor> {

    @Override
    public void run(SourceContext<Sensor> ctx) throws Exception {
      val random = ThreadLocalRandom.current();
      while (isRunning.get()) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
          val sensor = new Sensor();
          sensor.setId(StrUtil.format("sensor_{}", i));
          sensor.setTemperature(BigDecimal.valueOf(random.nextGaussian() * 100).setScale(2, 2));
          sensor.setTimestamp(System.currentTimeMillis());
          ctx.collect(sensor);
          TimeUnit.SECONDS.sleep(1);
        }
      }
    }

    @Override
    public void cancel() {
      isRunning.set(false);
    }

    private final static AtomicBoolean isRunning = new AtomicBoolean(true);
  }

}
