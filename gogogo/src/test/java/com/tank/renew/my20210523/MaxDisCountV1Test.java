package com.tank.renew.my20210523;

import com.google.common.collect.Lists;
import io.vavr.collection.Stream;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;

@DisplayName("second")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MaxDisCountV1Test {

  @Test
  @DisplayName("3商品3活动")
  void discount() {
    val maxDisCountV1 = new MaxDisCountV1();
    val item1 = new Goods().setSku("x").setC(0).setValue(4);
    val item2 = new Goods().setSku("y").setC(0).setValue(5);
    val item3 = new Goods().setSku("xyz").setC(0).setValue(6);
    val goods = Lists.<Goods>newArrayList();
    goods.addAll(Arrays.asList(item1, item2, item3));
    Integer[] c = new Integer[goods.size()];
    Integer[] price = new Integer[goods.size()];
    val sku = Stream.ofAll(goods).map(Goods::getSku).toJavaList();
    c = Stream.ofAll(goods).map(Goods::getC).toJavaList().toArray(c);
    price = Stream.ofAll(goods).map(Goods::getValue).toJavaList().toArray(price);
    val result = maxDisCountV1.discount(sku, c, price, goods);
    val expected = 18;
    Assertions.assertEquals(result, expected);
  }

  @Test
  @DisplayName("2商品3活动")
  void discount2() {
    val maxDisCountV1 = new MaxDisCountV1();
    val item1 = new Goods().setSku("x").setC(0).setValue(4);
    val item2 = new Goods().setSku("y").setC(0).setValue(5);
    val goods = Lists.<Goods>newArrayList();
    goods.addAll(Arrays.asList(item1, item2));
    val sku = Stream.ofAll(goods).map(Goods::getSku).toJavaList();
    Integer[] c = {0, 0, 0};
    Integer[] v = {4, 5, 6};
    val result = maxDisCountV1.discount(sku, c, v, goods);
    val expected = 9;
    Assertions.assertEquals(result, expected);
  }


  @Test
  @DisplayName("1商品3活动")
  void discount3() {
    val maxDisCountV1 = new MaxDisCountV1();
    val item3 = new Goods().setSku("xyz").setC(0).setValue(6);
    val goods = Lists.<Goods>newArrayList();
    goods.addAll(Arrays.asList(item3));
    val sku = Stream.ofAll(goods).map(Goods::getSku).toJavaList();
    val c = new Integer[]{0, 0, 0};
    val v = new Integer[]{4, 5, 6};
    val result = maxDisCountV1.discount(sku, c, v, goods);
    int expected = 6;
    Assertions.assertEquals(expected, result);
  }


  @Test
  @DisplayName("4商品4活动")
  void discount5() {
    val maxDisCountV1 = new MaxDisCountV1();
    val c = new Integer[]{0, 48, 0, 0};
    val v = new Integer[]{4, 20, 5, 6};
    val item1 = new Goods().setSku("x").setValue(4).setC(0);
    val item2 = new Goods().setSku("x").setValue(20).setC(48);
    val item3 = new Goods().setSku("y").setValue(5).setC(0);
    val item4 = new Goods().setSku("xyz").setValue(6).setC(0);
    val goods = Lists.<Goods>newArrayList();
    goods.addAll(Arrays.asList(item1, item2, item3, item4));
    val sku = Stream.ofAll(goods).map(Goods::getSku).toJavaList();
    val result = maxDisCountV1.discount(sku, c, v, goods);
    val expected = 25;
    Assertions.assertEquals(expected, result);
  }


  private MaxDiscount maxDiscount;
}