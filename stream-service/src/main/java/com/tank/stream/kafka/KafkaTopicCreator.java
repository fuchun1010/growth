package com.tank.stream.kafka;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.tank.stream.pojo.Worker;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.ResultKind;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import java.time.LocalDateTime;

import static org.apache.flink.table.api.Expressions.$;

/**
 * @author tank198435163.com
 */
public class KafkaTopicCreator {

  @SneakyThrows
  public void createTopic() {
    val settings = EnvironmentSettings.newInstance().useBlinkPlanner().build();
    val stream = StreamExecutionEnvironment.getExecutionEnvironment();

    stream.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);

    val tableEnvironment = StreamTableEnvironment.create(stream, settings);


    val ddlSql = "create table tab_employees (id BIGINT, name String, created String) " +
            "with ('connector' = 'kafka',\n" +
            "  'topic' = 'topic_employees',\n" +
            "  'properties.bootstrap.servers' = 'localhost:9092',\n" +
            "  'format' = 'json')";

    ResultKind resultKind = tableEnvironment.executeSql(ddlSql).getResultKind();

    if (resultKind == ResultKind.SUCCESS) {
      System.out.println("ok");
    }


    val created = DateUtil.format(LocalDateTime.now(), DatePattern.NORM_DATETIME_PATTERN);
    val created2 = DateUtil.format(LocalDateTime.now().plusSeconds(-2L), DatePattern.NORM_DATETIME_PATTERN);

    val streamSource = stream.fromElements(
            new Worker(1L, "jack", created),
            new Worker(2L, "lucy", created2)
    );

    tableEnvironment.createTemporaryView("memory_tab_employees",
            streamSource,
            $("id"),
            $("name"),
            $("created").as("created"));


    val memoryTabEmployees = tableEnvironment.sqlQuery("select id, name, created from memory_tab_employees");

    ResultKind insertKind = tableEnvironment.createStatementSet()
            .addInsert("tab_employees", memoryTabEmployees)
            .execute().getResultKind();

    if (insertKind == ResultKind.SUCCESS) {
      System.out.println("inserted success");
    }

    tableEnvironment.toAppendStream(tableEnvironment.from("tab_employees"), Row.class)
            .print();


    stream.execute(KafkaTopicCreator.class.getSimpleName());

  }

}
