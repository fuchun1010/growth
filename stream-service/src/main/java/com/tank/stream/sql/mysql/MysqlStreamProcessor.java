package com.tank.stream.sql.mysql;

import com.tank.stream.sql.StreamAction;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

import static org.apache.flink.table.api.Expressions.$;

/**
 * @author tank198435163.com
 */
public class MysqlStreamProcessor implements StreamAction {

  @Override
  @SneakyThrows
  public void process() {

    val env = initStreamEnvironment();

    val settings = EnvironmentSettings.newInstance().useBlinkPlanner().build();

    val tableEnv = StreamTableEnvironment.create(env, settings);

    env.setParallelism(1);

    val source = env.addSource(new MySqlDialogSource()).returns(Types.POJO(UserEvent.class));

    val table = tableEnv.fromDataStream(source)
            .select($("id"),
                    $("username"),
                    $("gender"),
                    $("job")
            );

    tableEnv.toAppendStream(table, Types.POJO(UserEvent.class)).print();


    env.execute("mysql");

  }

  private StreamExecutionEnvironment initStreamEnvironment() {
    val env = StreamExecutionEnvironment.getExecutionEnvironment();

    val checkPointCfg = env.getCheckpointConfig();
    checkPointCfg.setCheckpointTimeout(6000 * 2);
    checkPointCfg.setCheckpointInterval(6000);
    checkPointCfg.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
    return env;
  }

}
