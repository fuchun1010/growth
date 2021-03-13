package com.tank.stream.kafka;

import com.google.common.base.Preconditions;
import com.tank.stream.common.PropsBuilder;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.state.StateTtlConfig;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.function.Consumer;

/**
 * @author tank198435163.com
 */
public class KafkaStreamProcessor {

  public static KafkaStreamProcessor instance() {
    return PROCESSOR;
  }

  @SneakyThrows
  public <T> void processStream(@NonNull String topic,
                                @NonNull final String jobName,
                                @NonNull final PropsBuilder props,
                                @NonNull final DeserializationSchema<T> schema,
                                @NonNull final Consumer<DataStreamSource<T>> streamHandler) {
    val kafkaServersOpt = props.value("bootstrap.servers");
    Preconditions.checkArgument(kafkaServersOpt.isDefined(), "bootstrap.servers not defined");
    val groupOpt = props.value("group.id");
    Preconditions.checkArgument(groupOpt.isDefined(), "group.id not defined");
    val properties = props.toProps();
    val kafkaConsumer = new FlinkKafkaConsumer<T>(topic, schema, properties);
    //consumer offset config
    kafkaConsumer.setStartFromLatest();
    val env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
    env.getCheckpointConfig().setCheckpointInterval(ONE_MINUTE);
    val source = env.addSource(kafkaConsumer);
    streamHandler.accept(source);
    env.execute(jobName);
  }


  private KafkaStreamProcessor() {

  }

  private StateTtlConfig initStateTtlConfig() {
    return StateTtlConfig
            .newBuilder(Time.days(2))
            .setUpdateType(StateTtlConfig.UpdateType.OnCreateAndWrite)
            .setStateVisibility(StateTtlConfig.StateVisibility.NeverReturnExpired)
            .build();
  }

  private static final KafkaStreamProcessor PROCESSOR = new KafkaStreamProcessor();

  private long ONE_MINUTE = 1000 * 60;

}
