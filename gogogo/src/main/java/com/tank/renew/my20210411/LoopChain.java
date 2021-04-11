package com.tank.renew.my20210411;

import lombok.NonNull;

/**
 * @author tank198435163.com
 */
public interface LoopChain {

  boolean isLooped(@NonNull final Node<Integer> root);
}
