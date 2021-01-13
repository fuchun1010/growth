package com.tank.algorithm;

import com.google.common.collect.Maps;
import lombok.NonNull;
import lombok.val;

/**
 * @author tank198435163.com
 */
public class TwoIntAdding {

  public Integer[] indexForAdding(@NonNull final Integer target, @NonNull final Integer[] arr) {
    val mapping = Maps.<Integer, Integer>newHashMap();
    val length = arr.length;
    for (int index = 0; index < length; index++) {
      val data = arr[index];
      val differ = target - data;
      if (mapping.containsKey(differ)) {
        val startIndex = mapping.get(differ);
        return new Integer[]{startIndex, index};
      } else {
        mapping.putIfAbsent(data, index);
      }
    }

    return new Integer[2];
  }

}
