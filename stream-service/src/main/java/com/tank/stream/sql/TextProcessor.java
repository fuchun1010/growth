package com.tank.stream.sql;

import lombok.*;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import java.io.Serializable;

import static org.apache.flink.table.api.Expressions.$;

/**
 * @author tank198435163.com
 */
public class TextProcessor implements StreamAction {

  @Override
  @SneakyThrows
  public void process() {
    val env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.setParallelism(1);
    val checkPointCfg = env.getCheckpointConfig();
    checkPointCfg.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
    checkPointCfg.setCheckpointInterval(6000);
    val settings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
    val tableEnv = StreamTableEnvironment.create(env, settings);
    val workerStream = env.readTextFile("/Users/tank198435163.com/javadone/500w/growth/stream-service/src/main/resources/worker.txt")
            .map((MapFunction<String, Worker>) line -> {
              val fields = line.split(",");
              return new Worker(fields[0], Integer.parseInt(fields[1]), fields[2], fields[3], Integer.parseInt(fields[4]));
            })
            .returns(Types.POJO(Worker.class));


    tableEnv.createTemporaryView("workers", workerStream);


    val result = tableEnv.from("workers")
            .select($("name"), $("age"), $("address"), $("salary"))
            .filter($("salary").isGreater(5000));

    tableEnv.toAppendStream(result, Row.class).print();


    env.execute("simple-text");
  }

  /**
   * @author tank198435163.com
   */
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Worker implements Serializable {

    private String name;

    private Integer age;

    private String address;

    private String job;

    private Integer salary;
  }
}
