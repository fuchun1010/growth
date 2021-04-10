package com.tank.renew.my20210410;

import com.google.common.collect.Lists;
import lombok.NonNull;
import lombok.val;
import lombok.var;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Note implements
 * bucket
 *
 * @author tank198435163.com
 */
public class BucketSortingV1 implements Sorting {

  @Override
  public Integer[] sort(@NonNull Integer[] source) {

    var min = source[0];
    var max = source[0];

    for (int index = 1; index < source.length; index++) {
      Integer value = source[index];
      if (min > value) {
        min = value;
      }
      if (max < value) {
        max = value;
      }
    }

    val d = max - min;
    val num = source.length;

    val buckets = Lists.<LinkedList<Integer>>newArrayListWithCapacity(num);

    for (int index = 0; index < num; index++) {
      buckets.add(Lists.newLinkedList());
    }

    for (int value : source) {
      int targetIndex = ((value - min) * (num - 1) / d);
      buckets.get(targetIndex).add(value);
    }

    for (LinkedList<Integer> bucket : buckets) {
      Collections.sort(bucket);
    }

    Integer[] result = new Integer[source.length];
    var index = 0;

    for (LinkedList<Integer> bucket : buckets) {

      for (Integer value : bucket) {
        result[index++] = value;
      }
    }

    return result;
  }
}
