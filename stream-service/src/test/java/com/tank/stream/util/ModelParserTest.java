package com.tank.stream.util;

import cn.hutool.core.date.DateTime;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.junit.jupiter.api.*;

import java.util.Map;

/**
 * @author tank198435163.com
 */
@DisplayName("模型转换")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ModelParserTest {

  @Test
  @DisplayName("创建时间模型测试")
  void toEventModelWithCreated() {
    val createdEvent = this.instance.toEventModel(this.mockCreated());
    Assertions.assertNotNull(createdEvent);
    Assertions.assertTrue("c".equalsIgnoreCase(createdEvent.getOp()));
    Assertions.assertEquals(12, createdEvent.getFields().size());
  }


  @Test
  @DisplayName("转成执行泛型model")
  void toAppointType() {
    val model = this.instance.toEventModel(this.mockCreated());
    val order = model.transformTo(OrderV2.class);
    Assertions.assertNotNull(order);
  }

  @BeforeEach
  void init() {
    this.instance = ModelParser.instance();
  }

  @SuppressWarnings("unchecked")
  private Map<String, Object> mockCreated() {
    final Map<String, Object> result = Maps.newHashMap();
    result.putIfAbsent("op", "c");

    result.putIfAbsent("source", Maps.<String, Object>newHashMap());
    final Map<String, Object> source = ((Map<String, Object>) result.get("source"));
    source.putIfAbsent("server_id", 0);
    source.putIfAbsent("version", "1.4.1.Final");
    source.putIfAbsent("file", "mysql-bin.000039");
    source.putIfAbsent("connector", "mysql");
    source.putIfAbsent("pos", "154");
    source.putIfAbsent("name", "localhost-demo");
    source.putIfAbsent("ts_ms", 0);

    source.putIfAbsent("snapshot", "last");
    source.putIfAbsent("db", "ordercenter");
    source.put("table", "wxapp_scan_logs");

    //

    result.putIfAbsent("after", Maps.<String, Object>newHashMap());
    final Map<String, Object> after = ((Map<String, Object>) result.get("after"));

    after.putIfAbsent("store_code", "0090");
    after.putIfAbsent("driver_mobile", "18719033514");
    after.putIfAbsent("delivery_center", "深圳配送中心");
    after.putIfAbsent("created_at", "2020-04-21T08:58:09Z");
    after.putIfAbsent("confirm_time_type", "CHECKED");

    after.putIfAbsent("confirm_time_type", "CHECKED");
    after.putIfAbsent("version", 1);
    after.put("driver_name", "xx");
    after.put("brevity_code", "x1");
    after.put("updated_at", "2020-04-21T08:58:09Z");
    after.put("store_name", "丽阳天下店");
    after.put("code_value", "01w1234567#0087#184");
    after.put("id", 39922);

    return result;
  }

  private ModelParser instance;

  @Getter
  @Setter
  public static class OrderV2 {

    /**
     * store_code : 0090
     * driver_mobile : 18719033514
     * delivery_center : 深圳配送中心
     * created_at : 2020-04-21T08:58:09Z
     * confirm_time_type : CHECKED
     * version : 1
     * driver_name : 炒着吃
     * brevity_code : 5696
     * updated_at : 2020-04-21T08:58:09Z
     * store_name : 丽阳天下店
     * code_value : 01w1234567#0087#184
     * id : 39922
     * scan_time : 2020-04-21T08:58:10Z
     */

    private String storeCode;
    private String driverMobile;
    private String deliveryCenter;
    private String createdAt;
    private String confirmTimeType;
    private int version;
    private String driverName;
    private String brevityCode;
    private DateTime updatedAt;
    private String storeName;
    private String codeValue;
    private int id;
    private String scanTime;

  }


}