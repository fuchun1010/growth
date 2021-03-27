package com.tank.renew.my20210326;

import lombok.NonNull;

import java.util.Optional;

/**
 * @author tank198435163.com
 */
public interface LfuDef<K, V> {

  Optional<CacheNode<K, V>> find(@NonNull final K key);
}
