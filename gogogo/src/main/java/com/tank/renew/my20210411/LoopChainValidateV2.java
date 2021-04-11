package com.tank.renew.my20210411;

import lombok.NonNull;

import java.util.Objects;

/**
 * @author tank198435163.com
 */
public class LoopChainValidateV2 implements LoopChain {
  @Override
  public boolean isLooped(@NonNull Node<Integer> root) {

    Node<Integer> p = root;
    
    Node<Integer> next = Objects.nonNull(p.getNext()) ? p.getNext().getNext() : null;

    if (Objects.isNull(next)) {
      return false;
    }

    while (p != next) {
      p = p.getNext();
      next = Objects.nonNull(next.getNext()) ? next.getNext().getNext() : null;
      if (Objects.isNull(next)) {
        return false;
      }
    }

    return true;

  }
}
