package com.tank.renew.my20210506;

import lombok.NonNull;

import java.util.Optional;

/**
 * @author tank198435163.com
 */
public interface ThreeAdd {

  Optional<int[]> obtainIndexes(int value, @NonNull final int[] data);
}
