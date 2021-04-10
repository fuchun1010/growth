package com.tank.renew.my20210410;

import lombok.NonNull;
import lombok.val;
import lombok.var;

import java.util.Objects;

/**
 * @author tank198435163.com
 */
public class CounterSortingV2 implements Sorting {
  @Override
  public Integer[] sort(@NonNull Integer[] source) {

    var max = source[0];
    var min = source[0];

    for (int index = 1; index < source.length; index++) {
      var tmp = source[index];
      if (tmp > max) {
        max = tmp;
      }
      if (tmp < min) {
        min = tmp;
      }
    }

    int len = max - min + 1;

    final Integer[] newArr = new Integer[len];

    for (int index = 0; index < source.length; index++) {
      val newIndex = source[index] - min;
      if (Objects.isNull(newArr[newIndex])) {
        newArr[newIndex] = 0;
      }
      newArr[newIndex] += 1;
    }

    final Integer[] result = new Integer[source.length];
    int i = 0;

    for (int index = 0; index < newArr.length; index++) {
      val value = newArr[index];
      if (Objects.isNull(value)) {
        continue;
      }
      result[i++] = index + min;
    }

    return result;
  }
}
