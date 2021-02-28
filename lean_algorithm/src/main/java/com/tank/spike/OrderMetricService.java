package com.tank.spike;

import cn.hutool.core.collection.CollUtil;
import io.vavr.Function1;
import io.vavr.collection.Stream;
import lombok.NonNull;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author tank198435163.com
 */
public class OrderMetricService {

  /**
   * @param supplier
   * @return
   */
  public int accumulate(@NonNull final Supplier<Collection<OrderSample>> supplier,
                        Function1<OrderSample, Integer> success,
                        Function1<OrderSample, Integer> failure) {

    return CollUtil.isEmpty(supplier.get()) ? DEFAULT_COUNT : Stream.ofAll(supplier.get())
            .map(sample -> sample.isAdd() ? success.apply(sample) : failure.apply(sample))
            .reduce(Integer::sum);
  }


  public <K, R> Map<K, Stream<R>> groupBy(@NonNull final Supplier<Collection<R>> supplier,
                                          Function1<R, K> fun) {
    return Stream.ofAll(supplier.get()).groupBy(fun).toJavaMap();
  }

  private static final int DEFAULT_COUNT = 0;

}
