package com.tank.renew.my20210326;

import lombok.Getter;
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
public class CacheNode<K, V> {

  private K key;

  private V value;

  private CacheNode<K, V> next, prev;
}
