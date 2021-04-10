package com.tank.renew.my20210410;

import lombok.NonNull;
import lombok.val;
import lombok.var;

import java.util.Arrays;

/**
 * @author tank198435163.com
 */
public class SortingV3 implements Sorting {

  @Override
  public Integer[] sort(@NonNull Integer[] source) {
    var max = source[0];
    var min = source[0];
    var len = 0;

    for (int index = 1; index < source.length; index++) {
      val value = source[index];
      if (value > max) {
        max = value;
      }
      if (min > value) {
        min = value;
      }
    }

    len = max - min + 1;

    Integer[] newArr = new Integer[len];
    Arrays.fill(newArr, 0);

    for (Integer value : source) {
      val newIndex = value - min;
      newArr[newIndex] += 1;
    }

    for (int i = 1; i < newArr.length; i++) {
      newArr[i] += newArr[i - 1];
    }

    Integer[] result = new Integer[source.length];
    Arrays.fill(result, 0);

    for (int index = source.length - 1; index >= 0; index--) {
      val targetIndex = source[index] - min;
      val rank = newArr[targetIndex];
      newArr[targetIndex] -= 1;
      result[rank - 1] = source[index];
    }


    return result;
  }
}
