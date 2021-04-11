package com.tank.renew.my20210411;

import com.google.common.collect.Maps;
import lombok.NonNull;
import lombok.val;

import java.util.Objects;

/**
 * @author tank198435163.com 
 */
public class LoopChainValidateV1 implements LoopChain {

  @Override
  public boolean isLooped(@NonNull Node<Integer> root) {

    val cached = Maps.<Node<Integer>, Integer>newHashMap();

    while (Objects.nonNull(root)) {

      if (cached.containsKey(root)) {
        return true;
      }

      cached.put(root, 1);
      root = root.getNext();

    }

    return false;
  }
}
