package com.tank.stream.cdc;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import com.tank.stream.pojo.OrderV1;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import java.time.Duration;

/**
 * @author tank198435163.com
 */
public class CdcFlow {

  @SneakyThrows
  public void processEventFlow() {
    val env = StreamExecutionEnvironment.getExecutionEnvironment();
    val settings = EnvironmentSettings.newInstance().useBlinkPlanner().build();
    val tableEnv = StreamTableEnvironment.create(env, settings);
    tableEnv.getConfig().setIdleStateRetention(Duration.ofHours(1));
    env.setParallelism(Runtime.getRuntime().availableProcessors());
    val dataStream = this.createSimpleDataStream(env);

    tableEnv.createTemporaryView("tab_main_orders", dataStream);

    val tableResult = tableEnv.sqlQuery("select orgCode, sum(payment) payment from tab_main_orders group by orgCode");

    tableEnv.toRetractStream(tableResult, Row.class)
            .print();

    env.execute("cdc flow job");
  }

  private DataStreamSource<OrderV1> createSimpleDataStream(@NonNull final StreamExecutionEnvironment env) {
    val time = DateTime.now();
    val order1 = new OrderV1()
            .setCreated(time)
            .setCurrentStatus("created")
            .setOrderNo("1000")
            .setOrgCode("x2000")
            .setUpdated(time)
            .setPid(1L)
            .setPayment(100);
    val order2 = new OrderV1()
            .setCreated(time)
            .setCurrentStatus("cancel")
            .setOrderNo("1000")
            .setOrgCode("x2000")
            .setUpdated(time.offsetNew(DateField.MINUTE, 5))
            .setPid(1L).setPayment(-100);
    val order3 = new OrderV1()
            .setCreated(time)
            .setCurrentStatus("created")
            .setOrderNo("2000")
            .setOrgCode("x3000")
            .setUpdated(time.offsetNew(DateField.MINUTE, 2))
            .setPid(2L).setPayment(120);
    val order4 = new OrderV1()
            .setCreated(time)
            .setCurrentStatus("cancel")
            .setOrderNo("2000")
            .setOrgCode("x3000")
            .setUpdated(time.offsetNew(DateField.MINUTE, 9))
            .setPid(2L).setPayment(-30);
    return env.fromElements(order1, order2, order3, order4);
  }

}
