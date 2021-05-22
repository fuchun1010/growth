package com.tank.renew.my20210523;

import com.google.common.collect.Maps;
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
    val keys = Maps.<String, Goods>newHashMap();
    for (val item : Arrays.asList(item1, item2, item3)) {
      keys.put(item.getSku(), item);
    }
    val goods = Arrays.asList("x", "y", "xyz");
    val c = new int[]{0, 0, 0};
    val v = new int[]{4, 5, 6};
    val result = maxDisCountV1.discount(goods, c, v, keys);
    Assertions.assertEquals(result, 11);
  }

  @Test
  @DisplayName("2商品3活动")
  void discount2() {
    val maxDisCountV1 = new MaxDisCountV1();
    val goods = Arrays.asList("x", "y");
    val c = new int[]{0, 0, 0};
    val v = new int[]{4, 5, 6};
    val item1 = new Goods().setSku("x").setC(0).setValue(4);
    val item2 = new Goods().setSku("y").setC(0).setValue(5);
    val item3 = new Goods().setSku("z").setC(0).setValue(6);
    val keys = Maps.<String, Goods>newHashMap();
    for (val item : Arrays.asList(item1, item2, item3)) {
      keys.put(item.getSku(), item);
    }
    val result = maxDisCountV1.discount(goods, c, v, keys);
    Assertions.assertEquals(result, 9);
  }

  @Test
  @DisplayName("1商品3活动")
  void discount3() {
    val maxDisCountV1 = new MaxDisCountV1();
    val item1 = new Goods().setSku("x").setC(0).setValue(4);
    val item2 = new Goods().setSku("y").setC(0).setValue(5);
    val item3 = new Goods().setSku("xyz").setC(9).setValue(6);
    val keys = Maps.<String, Goods>newHashMap();
    for (val item : Arrays.asList(item1, item2, item3)) {
      keys.put(item.getSku(), item);
    }
    val goods = Arrays.asList("x");
    val c = new int[]{0, 0, 0};
    val v = new int[]{4, 5, 6};
    val result = maxDisCountV1.discount(goods, c, v, keys);
    Assertions.assertEquals(6, result);
  }


  @Test
  @DisplayName("4商品4活动")
  void discount5() {
    val maxDisCountV1 = new MaxDisCountV1();
    val goods = Arrays.asList("x", "x", "y", "xyz");
    val c = new int[]{0, 48, 0, 0};
    val v = new int[]{4, 20, 5, 6};
    val item1 = new Goods().setSku("x").setValue(4).setC(0);
    val item2 = new Goods().setSku("x").setValue(20).setC(48);
    val item3 = new Goods().setSku("y").setValue(5).setC(0);
    val item4 = new Goods().setSku("xyz").setValue(6).setC(0);
    val keys = Maps.<String, Goods>newHashMap();
    for (val item : Arrays.asList(item1, item2, item3, item4)) {
      keys.put(item.getSku(), item);
    }
    val result = maxDisCountV1.discount(goods, c, v, keys);
    Assertions.assertEquals(25, result);
  }


  private MaxDiscount maxDiscount;
}