package com.tank.renew.my20210425;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import lombok.NonNull;
import lombok.val;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 拆单
 * @author tank198435163.com
 */
public class OrderSpliter {

  public Collection<List<Item>> splitted(
          @NonNull final List<Item> items,
          @NonNull final Queue<String> props) {

    while (!props.isEmpty()) {
      val methodName = StrUtil.format("get{}", props.remove());
      val getter = Arrays.stream(Item.class
              .getDeclaredMethods())
              .filter(method -> method.getName().equalsIgnoreCase(methodName))
              .findFirst();

      if (!getter.isPresent()) {
        return Lists.newArrayList();
      }

      val result = items.stream().collect(Collectors.groupingBy(d -> {
        try {
          return getter.get().invoke(d);
        } catch (Exception e) {
          e.printStackTrace();
        }
        return null;
      }));

      if (result == null) {
        continue;
      }

      for (Map.Entry<Object, List<Item>> entry : result.entrySet()) {
        if (entry.getValue().isEmpty()) {
          continue;
        }

        if (props.isEmpty()) {
          lists.add(entry.getValue());
        }

        this.splitted(entry.getValue(), props);
      }

    }

    return lists;
  }

  private final List<List<Item>> lists = Lists.newLinkedList();
}
