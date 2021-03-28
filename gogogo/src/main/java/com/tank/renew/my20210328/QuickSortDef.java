package com.tank.renew.my20210328;

import lombok.NonNull;

/**
 * @param <T>
 * @author tank198435163.com
 */
public interface QuickSortDef<T extends Comparable<T>> {


  T[] sort(@NonNull final T[] arr);

}
