package com.tank.renew.my20210403;

import lombok.NonNull;

/**
 * @param <T>
 * @author tank198435163.com
 */
public interface HeapDef<T extends Comparable<T>> {

  int size();

  T[] buildHeap(@NonNull final T[] array, @NonNull final Class<T> clazz);
}
