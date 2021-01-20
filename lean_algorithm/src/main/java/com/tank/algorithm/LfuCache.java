package com.tank.algorithm;

import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import io.vavr.Function1;
import io.vavr.Tuple2;
import io.vavr.collection.Stream;
import io.vavr.control.Option;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;

import java.lang.reflect.Array;
import java.nio.file.AccessDeniedException;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * @param <K>
 * @param <V>
 * @author tank198435163.comV
 */
public class LfuCache<K extends Comparable<K>, V> {

  @SuppressWarnings("unchecked")
  public LfuCache(int capacity) {
    this.array = ((Tuple2<V, Integer>[]) Array.newInstance(Tuple2.class, capacity));
  }


  public Option<V> obtain(@NonNull final K key,
                          @NonNull final Function1<V, K> func) {
    val k = this.indexMapping.get(key);
    if (Objects.isNull(k)) {
      return Option.none();
    }
    val result = this.array[k];
    synchronized (lock) {
      for (int index = 0; index < this.capacity; index++) {
        val item = this.array[index];
        int counter;
        if (Objects.nonNull(result) && result._1().equals(item._1())) {
          counter = item._2() + 1;
          val updatedValue = item.update2(counter);
          this.array[index] = updatedValue;
          continue;
        }
        counter = item._2() - 1;
        if (counter > 0) {
          this.array[index] = item.update2(counter);
        } else {
          this.array[index] = null;
          queue.add(index);
          this.indexMapping.remove(func.apply(item._1));
        }

      }

    }
    return Option.of(this.array[k]._1());
  }

  @SneakyThrows({AccessDeniedException.class})
  public void addItem(@NonNull V item, @NonNull final Function1<V, K> func) {
    if (this.queue.isEmpty()) {
      throw new AccessDeniedException("cache is full");
    }
    synchronized (lock) {
      this.capacity++;
      val index = this.queue.poll();
      if (Objects.isNull(index)) {
        return;
      }
      this.indexMapping.put(func.apply(item), index);
      this.array[index] = new Tuple2<>(item, index);
    }

  }

  public void initialize(@NonNull final Collection<V> collection, @NonNull final Function<V, K> function) {
    val counter = new AtomicInteger(0);
    this.capacity += collection.size();
    synchronized (lock) {
      Stream.ofAll(collection)
              .map(item -> new Tuple2<V, Integer>(item, 20))
              .forEach(item -> {
                val index = counter.getAndIncrement();
                this.indexMapping.put(function.apply(item._1), index);
                this.array[index] = item;
              });
    }
  }


  private Tuple2<V, Integer>[] array;

  private int capacity;

  private final Queue<Integer> queue = Queues.newArrayDeque();

  private final Map<K, Integer> indexMapping = Maps.newConcurrentMap();

  private final byte[] lock = new byte[1];
}
