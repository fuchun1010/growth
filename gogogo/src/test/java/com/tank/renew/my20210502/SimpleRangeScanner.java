package com.tank.renew.my20210502;

import com.google.common.collect.Lists;
import io.vavr.Tuple2;
import io.vavr.collection.Stream;
import lombok.NonNull;
import lombok.val;

import java.util.List;

/**
 * @author tank198435163.com
 */
public class SimpleRangeScanner {


  public SimpleRangeScanner(@NonNull final Integer bucketNum,
                            @NonNull final Integer... array) {
    if (bucketNum.compareTo(0) <= 0) {
      throw new IllegalArgumentException("not allowed bucketNum less than zero");
    }
    this.targets = array;
    this.bucketNum = bucketNum;
  }

  public List<Bucket> put() {
    val buckets = this.splitBucket();

    for (final Integer target : this.targets) {
      for (Bucket bucket : buckets) {
        val contains = Stream.range(bucket.getMin(), bucket.getMax()).contains(target);
        if (contains) {
          bucket.add(target);
          break;
        }
      }
    }

    return buckets;
  }


  private List<Bucket> splitBucket() {
    val tuple2 = this.obtainMinAndMax();
    val min = tuple2._1();
    val max = tuple2._2();

    val step = max.compareTo(min) == 0 ? 1 : (int) Math.ceil((max - min) / this.bucketNum) + 1;
    val buckets = Lists.<Bucket>newArrayList();
    for (int index = 0; index < this.bucketNum; index++) {
      val preMin = index == 0 ? min : buckets.get(index - 1).getMax();
      val bucket = new Bucket().setMin(preMin).setMax(preMin + step);
      buckets.add(bucket);
    }
    return buckets;
  }


  private Tuple2<Integer, Integer> obtainMinAndMax() {
    this.min = this.max = Stream.of(this.targets).head();

    for (Integer target : Stream.of(this.targets).tail()) {
      if (target.compareTo(this.max) >= 0) {
        this.max = target;
      }

      if (target.compareTo(min) < 0) {
        this.min = target;
      }
    }

    return new Tuple2<>(this.min, this.max);

  }

  private Integer min = null;

  private Integer max = null;

  private Integer[] targets;

  private Integer bucketNum = 0;

}

