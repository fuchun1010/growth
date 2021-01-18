package com.tank.sorting;

import lombok.NonNull;
import lombok.val;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @param <T>
 * @author tank198435163.com
 */
public class Bubble<T extends Comparable<T>> implements Sorting<T> {

  @SuppressWarnings("unchecked")
  public Bubble(@NonNull final Collection<T> collection,
                @NonNull final Class<T> clazz) {
    int length = collection.size();
    this.array = ((T[]) Array.newInstance(clazz, length));
    new ArrayList<>(collection).toArray(this.array);
  }

  @Override
  public T[] sort() {
    return this.sortAsc();
  }

  private T[] sortAsc() {
    final T[] targets = Arrays.copyOfRange(this.array, 0, this.array.length - 1);
    val len = targets.length;
    for (int i = 0; i < len; i++) {
      for (int j = i + 1; j < len; j++) {
        T next = targets[j];
        val isGreater = targets[i].compareTo(next) > 0;
        if (isGreater) {
          T tmp = targets[i];
          targets[i] = targets[j];
          targets[j] = tmp;
        }

      }
    }
    return targets;
  }


  private final T[] array;


}
