package com.tank.renew.my20210326;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Optional;

/**
 * @param <K>
 * @param <V>
 * @author tank198435163.com
 */
public class LfuDefImpl<K, V> implements LfuDef<K, V> {

  public LfuDefImpl() {
    super();
  }

  @Override
  public Optional<CacheNode<K, V>> find(K key) {
    return Optional.empty();
  }


  private Map<K, V> mappings = Maps.newConcurrentMap();
}
