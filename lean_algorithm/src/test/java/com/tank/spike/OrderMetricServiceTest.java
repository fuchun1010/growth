package com.tank.spike;

import io.vavr.collection.Stream;
import lombok.val;
import lombok.var;
import org.junit.jupiter.api.*;

import java.util.Collection;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

/**
 * @author tank198435163.com
 */
@DisplayName("订单指标统计测试")
@TestInstance(PER_CLASS)
class OrderMetricServiceTest {


  @Test
  @DisplayName("订单量统计测试")
  void accumulateCount() {
    val count = this.orderMetricService.accumulate(this::initOrderSamples, sample -> 1, sample -> -1);
    Assertions.assertEquals(0, count);
  }

  @Test
  @DisplayName("订单销售金额")
  void accumulateSales() {
    val sales = this.orderMetricService.accumulate(this::initOrderSamples, OrderSample::getTotalPrice, sample -> -sample.getTotalPrice());
    Assertions.assertTrue(sales < 0);
  }

  @Test
  @DisplayName("订单动态分组")
  void groupBy() {
    var result = this.orderMetricService.groupBy(this::initOrderSamples, OrderSample::getAreaCode);
    Assertions.assertEquals(2, result.size());
    result = this.orderMetricService.groupBy(this::initOrderSamples, OrderSample::getChannel);
    Assertions.assertEquals(1, result.size());
  }

  @BeforeEach
  void initialize() {
    this.orderMetricService = new OrderMetricService();
  }


  /**
   * @return
   */
  private Collection<OrderSample> initOrderSamples() {
    val orderSample1 = new OrderSample("16023947192247545", 8470, "1", "4200000764202010118698746516", false, "2020-10-11 13:38:39", "region_1002", "area_njpszx", "oms", "piece_0102500007", "1", "store_770152");
    val orderSample2 = new OrderSample("16023962538380648", 5989, "1", "4200000767202010114484439609", true, "2020-10-11 14:04:13", "region_1003", "area_gzpszx", "oms", "piece_0102000016", "1", "store_1024");
    return Stream.of(orderSample1, orderSample2).toJavaList();
  }

  private OrderMetricService orderMetricService;
}