package com.tank.stream.udf.demo;

import com.tank.stream.pojo.WordV2;
import com.tank.stream.udf.RowsCntUdf;
import lombok.*;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import static org.apache.flink.table.api.Expressions.$;

/**
 * @author tank198435163.com
 */
public class CounterForUdf {

  @SneakyThrows
  public void statistics(@NonNull final String... words) {
    val environment = StreamExecutionEnvironment.getExecutionEnvironment();
    environment.setParallelism(1);
    val settings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
    val tableEnv = StreamTableEnvironment.create(environment, settings);

    val source = environment.fromElements(words).map(new MapFunction<String, WordV2>() {
      @Override
      public WordV2 map(String value) throws Exception {
        return new WordV2(value.length());
      }
    });

    tableEnv.createTemporaryFunction("rowCount", RowsCntUdf.class);

    tableEnv.createTemporaryView("words", source, $("cnt"));


    val sql = "select rowCount(cnt) as cnt from words";

    Table table = tableEnv.sqlQuery(sql);

    tableEnv.toRetractStream(table, Row.class).print();

    environment.execute("CounterForUdf");
  }




}
