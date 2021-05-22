package com.tank.renew.my20210506;

import com.google.common.collect.Maps;
import lombok.NonNull;
import lombok.val;
import lombok.var;

import java.util.Optional;

/**
 * [1,2,3,10,13]  26 => [0,1,2]
 * @author tank198435163.com
 */
public class DefaultThreeAddImpl implements ThreeAdd {

  @Override
  public Optional<int[]> obtainIndexes(int value, @NonNull int[] data) {
    int[] target = new int[data.length];
    System.arraycopy(data, 0, target, 0, data.length);
    this.quickSort(target, 0, target.length);
    this.data = target;
    var leftIndex = 0;
    var rightIndex = target.length - 1;
    val indexesMapped = Maps.<Integer, Integer>newHashMap();
    for (int index = 0; index < target.length; index++) {
      indexesMapped.put(target[index], index);
    }

    while (rightIndex != leftIndex) {
      val result = value - this.data[leftIndex] - this.data[rightIndex];
      if (indexesMapped.containsKey(result)) {
        return Optional.of(new int[]{leftIndex, indexesMapped.get(result), rightIndex});
      }
      if (result > 0) {
        leftIndex++;
        continue;
      }
      if (result < 0) {
        rightIndex--;
      }
    }
    return Optional.empty();
  }


  private int markIndex(int start, int end, int[] target) {

    var first = target[start];
    var mark = start;
    for (int index = start + 1; index < end; index++) {
      var current = target[index];
      if (current < first) {
        mark++;
        target[index] = target[mark];
        target[mark] = current;
      }
    }
    target[start] = target[mark];
    target[mark] = first;
    return mark;
  }


  private void quickSort(int[] target, int start, int end) {
    if (start >= end) {
      return;
    }
    val markIndex = this.markIndex(start, end, target);
    this.quickSort(target, start, markIndex);
    this.quickSort(target, markIndex + 1, end);
  }

  private int[] data;

}
