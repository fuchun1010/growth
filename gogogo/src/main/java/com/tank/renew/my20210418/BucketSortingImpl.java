package com.tank.renew.my20210418;

import com.google.common.collect.Lists;
import io.vavr.collection.Stream;
import lombok.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author tank198435163.com
 */
public class BucketSortingImpl implements Sort {

  @Override
  public Integer[] sorted(@NonNull int[] arr) {
    val buckets = Lists.<Bucket>newArrayList();
    var min = arr[0];
    var max = min;

    for (int index = 0; index < arr.length; index++) {
      buckets.add(new Bucket());
    }

    for (int index = 1; index < arr.length; index++) {

      val value = arr[index];
      if (value > max) {
        max = value;
      }

      if (value < min) {
        min = value;
      }
    }

    val differ = max - min;
    val len = arr.length;

    for (int d : arr) {
      val newIndex = (d - min) * (len - 1) / differ;
      if (newIndex > buckets.size()) {
        continue;
      }
      buckets.get(newIndex).add(d);
    }

    Bucket result = Stream.ofAll(buckets)
            .filter(d -> !d.collections.isEmpty()).toList().reduce((a, b) -> {
              a.collections.addAll(b.collections);
              return a;
            });

    Integer[] newArr = new Integer[result.collections.size()];

    result.collections.toArray(newArr);

    Arrays.sort(newArr);

    return newArr;
  }


  private static class Bucket {

    public void add(int num) {
      this.collections.add(num);
    }

    @Getter
    @Setter
    private List<Integer> collections = Lists.newArrayList();
  }
}
