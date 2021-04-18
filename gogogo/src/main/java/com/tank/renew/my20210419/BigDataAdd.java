package com.tank.renew.my20210419;

import lombok.NonNull;

/**
 * 2个大数相加
 *
 * @author tank198435163.com
 */
public interface BigDataAdd {

  /**
   * add tow big integer
   *
   * @param first
   * @param second
   * @return
   */
  String add(@NonNull final String first, @NonNull final String second);
}
