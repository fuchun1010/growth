package com.tank.renew.my20210417;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.*;

import java.util.stream.IntStream;

/**
 * @author tank198435163.com
 */
public class MaxDifferImpl implements MaxDiffer {
  
  @Override
  public int maxDistance(@NonNull int[] arr) {
    int length = arr.length;
    val buckets = Lists.<Bucket>newArrayList();
    IntStream.range(0, length).boxed().forEach(index -> buckets.add(new Bucket()));

    Preconditions.checkArgument(length > 0, "array not allowed empty");
    var min = arr[0];
    var max = arr[0];

    for (int i = 1; i < length - 1; i++) {
      val target = arr[i];
      if (target < min) {
        min = target;
      }
      if (target > max) {
        max = target;
      }
    }

    val differ = max - min;

    if (differ == 0) {
      return 0;
    }

    for (int value : arr) {
      val index = (value - min) * (length - 1) / differ;
      Bucket bucket = buckets.get(index);
      if (bucket.min == null || bucket.min > value) {
        bucket.min = value;
      }
      if (bucket.max == null || bucket.max < value) {
        bucket.max = value;
      }
    }

    var maxDistance = Integer.MIN_VALUE;
    var leftMax = buckets.get(0).max;
    for (int index = 1; index < buckets.size(); index++) {
      val bucket = buckets.get(index);
      if (bucket.getMin() == null) {
        continue;
      }
      int d = bucket.getMin() - leftMax;
      if (d > maxDistance) {
        maxDistance = d;
      }
      leftMax = bucket.getMax();
    }

    return maxDistance;
  }

  @Getter
  @Setter
  private static class Bucket {

    private Integer max;
    private Integer min;
  }
}
