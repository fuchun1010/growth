package com.tank.renew.my20210326;

import com.google.common.collect.Maps;
import lombok.NonNull;
import lombok.val;

import java.util.Map;
import java.util.Optional;

public class LfuDefImpl<I, T> implements LfuDef<I, T> {
  @Override
  public Optional<T> findAny(@NonNull I input) {
    synchronized (lock) {
      val result = this.maps.get(input);
      if (result == null) {
        ddChain.delHeader();
        //TODO 删除map中的key

      } else {
        return Optional.of(result);
      }
    }
    return Optional.empty();
  }


  private final byte[] lock = new byte[1];

  private final Map<I, T> maps = Maps.newConcurrentMap();

  private DoubleChain<T> ddChain = new DoubleChain<>();
}
