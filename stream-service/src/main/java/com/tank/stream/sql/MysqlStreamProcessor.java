package com.tank.stream.sql;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.flink.connector.jdbc.catalog.JdbcCatalog;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * @author tank198435163.com
 */
public class MysqlStreamProcessor implements StreamAction {

  @Override
  @SneakyThrows
  public void process() {

    val env = StreamExecutionEnvironment.getExecutionEnvironment();

    val checkPointCfg = env.getCheckpointConfig();
    checkPointCfg.setCheckpointTimeout(6000 * 2);
    checkPointCfg.setCheckpointInterval(6000);
    checkPointCfg.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);

    val settings = EnvironmentSettings.newInstance().useBlinkPlanner().build();

    val tableEnv = StreamTableEnvironment.create(env, settings);

    String name = "demo";
    String defaultDatabase = "demo";
    String username = "root";
    String password = "123";
    String baseUrl = "jdbc:mysql://localhost:3307";

    JdbcCatalog catalog = new JdbcCatalog(name, defaultDatabase, username, password, baseUrl);
    tableEnv.registerCatalog("demo", catalog);

    env.execute("users");
  }

}
