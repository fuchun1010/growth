package com.tank.renew.my20210326;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @param <K>
 * @param <V>
 * @author tank198435163.com
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class CacheNode<K, V> {

  public CacheNode(@NonNull final K key, @NonNull final V value) {
    this.key = key;
    this.value = value;
  }

  private K key;

  private V value;

  private CacheNode<K, V> next, prev;
}
