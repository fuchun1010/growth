package com.tank.renew.my20210330;

import lombok.NonNull;

/**
 * 快排
 *
 * @param <T>
 * @author tank198435163.com
 */
public interface MarkIndex<T extends Comparable<T>> {

  /**
   * find the suitable index distinguish
   *
   * @param arr
   * @param start
   * @param end
   * @return
   */
  int splitIndex(@NonNull final T[] arr, int start, int end);

}
