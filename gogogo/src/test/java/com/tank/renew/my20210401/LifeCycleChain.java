package com.tank.renew.my20210401;

import com.google.common.collect.Maps;
import io.vavr.collection.Stream;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.NonNull;
import lombok.val;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * @param <T>
 * @author tank198435163.com
 */
public class LifeCycleChain<K, T> {

  @SuppressWarnings("unchecked")
  public void init(@NonNull final Function<T, K> keyFun, @NonNull final T... data) {
    val lifeNodes = Stream.of(data).map(LifeNode::new).toJavaList();
    final LifeNode<T>[] arr = new LifeNode[lifeNodes.size()];
    lifeNodes.toArray(arr);
    for (int i = 0; i < arr.length; i++) {
      val node = arr[i];
      val key = keyFun.apply(node.getData());
      mappings.put(key, node);
      if (i + 1 < arr.length) {
        node.next = arr[i + 1];
        if (i > 0) {
          arr[i + 1].pre = node;
        }
      }
    }

  }

  public Option<T> nextLifeNodeValue(@NonNull final K key) {
    return Option.some(
            Try.of(() -> this.mappings.get(key))
                    .filter(Objects::nonNull)
                    .map(LifeNode::getNext)
                    .filter(Objects::nonNull)
                    .map(LifeNode::getData).get());
  }


  private final Map<K, LifeNode<T>> mappings = Maps.newConcurrentMap();

  private final byte[] lock = new byte[1];
}
