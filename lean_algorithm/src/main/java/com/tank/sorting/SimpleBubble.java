package com.tank.sorting;

import lombok.NonNull;
import lombok.val;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author tank198435163.com
 */
public class SimpleBubble<T> {

  @SuppressWarnings("unchecked")
  public SimpleBubble(@NonNull final Collection<T> collection,
                      @NonNull final Class<T> clazz) {

    this.clazz = clazz;
    val size = collection.size();
    this.array = ((T[]) Array.newInstance(clazz, size));
    new ArrayList<>(collection).toArray(this.array);
  }


  public T[] highPerformanceSort(@NonNull final Comparator<T> comparator) {
    final T[] targetArray = this.initializeArray();
    val length = targetArray.length;

    for (int i = 0; i < length - 1; i++) {
      boolean isSorted = true;
      int sortBorder = length - 1;
      for (int j = 0; j < sortBorder; j++) {
        T first = targetArray[j];
        T second = targetArray[j + 1];
        boolean isGreater = comparator.compare(first, second) > 0;
        if (isGreater) {
          T tmp = targetArray[j];
          targetArray[j] = targetArray[j + 1];
          targetArray[j + 1] = tmp;
          isSorted = false;
          sortBorder = j;
        }
        this.counter.incrementAndGet();
      }

      if (isSorted) {
        break;
      }
    }

    return targetArray;
  }

  @SuppressWarnings("unchecked")
  private T[] initializeArray() {
    final T[] targetArray = ((T[]) Array.newInstance(this.clazz, this.array.length));
    Arrays.stream(this.array).collect(Collectors.toList()).toArray(targetArray);
    return targetArray;
  }

  public T[] sort(@NonNull final Comparator<T> comparator) {

    int length = this.array.length;

    for (int i = 0; i < length - 1; i++) {
      boolean isSorted = false;
      for (int j = 0; j < length - i - 1; j++) {
        T f = this.array[j];
        T s = this.array[j + 1];
        counter.incrementAndGet();
        if (comparator.compare(f, s) > 0) {
          T tmp = this.array[j];
          this.array[j] = this.array[j + 1];
          this.array[j + 1] = tmp;
          isSorted = true;
        }
      }

      if (!isSorted) {
        break;
      }
    }

    return this.array;
  }

  public int compareTimes() {
    return this.counter.get();
  }

  private Comparator<T> comparator;

  private Class<T> clazz;

  private final T[] array;

  private final AtomicInteger counter = new AtomicInteger(0);
}
