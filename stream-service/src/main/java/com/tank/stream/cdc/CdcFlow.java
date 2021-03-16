package com.tank.stream.cdc;

import com.tank.stream.pojo.OrderV1;
import com.tank.stream.udf.DateTimeToMillionSecondsUdf;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

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

    tableEnv.createTemporaryFunction("toMillionSec", DateTimeToMillionSecondsUdf.class);
    tableEnv.createTemporaryView("tab_main_orders", dataStream);

    val sqlV1 = "select * from (select orgCode, payment,currentStatus , orderNo,row_number() over(partition by orderNo order by updated asc) as rownum from tab_main_orders ) t1 where rownum = 1";

    val sqlV2 = "select * from tab_main_orders order by updated desc";

    val sqlV3 = "select toMillionSec(updated) as updated  from tab_main_orders";

    val sqlV4 = "select orderNo, currentStatus, sum(payment) as payment from tab_main_orders group by orderNo, currentStatus";

    val tableResult = tableEnv.sqlQuery(sqlV1);


    tableEnv.toRetractStream(tableResult, Row.class)
            .print();

    env.execute("cdc flow job");
  }

  private DataStreamSource<OrderV1> createSimpleDataStream(@NonNull final StreamExecutionEnvironment env) {
    val time = LocalDateTime.now();

    val order1 = new OrderV1()
            .setCreated(Timestamp.valueOf(time))
            .setCurrentStatus("created")
            .setOrderNo("1000")
            .setOrgCode("x2000")
            .setUpdated(Timestamp.valueOf(time))
            .setPid(1L)
            .setPayment(100);
    val order2 = new OrderV1()
            .setCreated(Timestamp.valueOf(time))
            .setCurrentStatus("cancel")
            .setOrderNo("1000")
            .setOrgCode("x2000")
            .setUpdated(Timestamp.valueOf(time.plusMinutes(5)))
            .setPid(1L).setPayment(-100);
    val order3 = new OrderV1()
            .setCreated(Timestamp.valueOf(time))
            .setCurrentStatus("created")
            .setOrderNo("2000")
            .setOrgCode("x3000")
            .setUpdated(Timestamp.valueOf(time.plusMinutes(2)))
            .setPid(2L).setPayment(120);
    val order4 = new OrderV1()
            .setCreated(Timestamp.valueOf(time))
            .setCurrentStatus("cancel")
            .setOrderNo("2000")
            .setOrgCode("x3000")
            .setUpdated(Timestamp.valueOf(time.plusMinutes(9)))
            .setPid(2L).setPayment(-30);
    return env.fromElements(order1, order2, order3, order4);
  }

}
