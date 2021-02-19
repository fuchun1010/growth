package com.tank.spike;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

/**
 * @author tank198435163.com
 */
class ObjectSerialTest {

  @Test
  @DisplayName("测试压缩大小")
  void compressTest() {
    val item = new Item();

    IntStream.rangeClosed(1, 2000)
            .boxed()
            .map(index -> createItem(111042, "招牌-法兰西西梅（中）".trim(), 255, 2, "2020-09-08"))
            .forEach(item.getData()::add);

    val jsonBytes = JSONUtil.toJsonStr(item).getBytes();
    byte[] protoBytes = toBytes(item);
    Assertions.assertTrue(jsonBytes.length > protoBytes.length);
    System.out.println("json bytes = " + jsonBytes.length);
    System.out.println("protoBytes bytes = " + protoBytes.length);
  }

  private Item.DataBean createItem(int code, String name, int amt, int cnt, String date) {
    Item.DataBean dataBean = new Item.DataBean();
    dataBean.setCode(code);
    dataBean.setName(name);
    dataBean.setAmt(amt);
    dataBean.setDate(date);
    dataBean.setCnt(cnt);
    return dataBean;
  }

  private byte[] toBytes(Item item) {
    val schema = RuntimeSchema.getSchema(Item.class);
    val buffer = LinkedBuffer.allocate(512);
    return ProtostuffIOUtil.toByteArray(item, schema, buffer);
  }


  @Getter
  @Setter
  private static class Item {

    private List<DataBean> data = Lists.newArrayList();

    @Getter
    @Setter
    public static class DataBean {
      /**
       * code : 1000
       * name : Apple
       * amt : 19098
       * cnt : 10
       * date : 2020-11-11
       */

      private int code;
      private String name;
      private int amt;
      private int cnt;
      private String date;
    }
  }
}
