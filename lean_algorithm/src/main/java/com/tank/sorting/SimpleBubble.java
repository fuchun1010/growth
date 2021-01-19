package com.tank.sorting;

import lombok.NonNull;
import lombok.val;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

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
