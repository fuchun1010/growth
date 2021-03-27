package com.tank.renew.my20210326;

import com.google.common.collect.Maps;
import lombok.NonNull;
import lombok.val;

import java.util.Map;
import java.util.Objects;
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
    val cacheNode = this.mappings.get(key);
    if (Objects.nonNull(cacheNode)) {
      return Optional.of(cacheNode);
    } else {
      synchronized (lock) {
        K targetKey = this.doubleChain.delHead();
        this.mappings.remove(targetKey);
      }
    }
    return Optional.empty();
  }


  @Override
  @SafeVarargs
  public final void init(@NonNull CacheNode<K, V>... cacheNodes) {
    synchronized (lock) {
      for (CacheNode<K, V> cacheNode : cacheNodes) {
        this.doubleChain.addCacheNode(cacheNode.getKey(), cacheNode.getValue());
        this.mappings.putIfAbsent(cacheNode.getKey(), cacheNode);
      }
    }
  }

  private final byte[] lock = new byte[1];
  private final DoubleChain<K, V> doubleChain = new DoubleChain<>();
  private final Map<K, CacheNode<K, V>> mappings = Maps.newConcurrentMap();
}
