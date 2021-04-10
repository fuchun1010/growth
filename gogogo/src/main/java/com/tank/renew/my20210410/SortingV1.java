package com.tank.renew.my20210410;

import lombok.NonNull;
import lombok.val;
import lombok.var;

import java.util.Objects;

/**
 * Note implements
 * simple counter sorting
 *
 * @author tank198435163.com
 */
public class SortingV1 implements Sorting {

  @Override
  public Integer[] sort(@NonNull Integer[] source) {
    var max = source[0];
    for (int index = 1; index < source.length; index++) {
      val tmp = source[index];
      if (tmp > max) {
        max = tmp;
      }
    }
    val len = max + 1;
    final Integer[] newArr = new Integer[len];

    for (Integer value : source) {
      if (Objects.isNull(newArr[value])) {
        newArr[value] = 0;
      }
      newArr[value] += 1;
    }

    var i = 0;
    final Integer[] result = new Integer[source.length];
    for (int index = 0; index < newArr.length; index++) {
      if (newArr[index] == null) {
        continue;
      }
      result[i++] = index;
    }

    return result;
  }

}
