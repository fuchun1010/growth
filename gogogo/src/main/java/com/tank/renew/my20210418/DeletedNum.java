package com.tank.renew.my20210418;

import lombok.NonNull;

/**
 * note
 * <p>
 * 1593212
 *
 * @author tank198435163.com
 */
public interface DeletedNum {

  /**
   * input num
   * remove k number
   *
   * @param num
   * @param k
   * @return
   */
  int remainingMin(@NonNull final String num, int k);
}
