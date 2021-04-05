package com.tank.renew.guess;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Queues;
import lombok.NonNull;
import lombok.val;

import java.util.Deque;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author tank198435163.com
 */
public class MaxConcurrencyReq {
  public static void main(String[] args) {

    val deque = Queues.newArrayDeque();
    IntStream.rangeClosed(1, 5).boxed().forEach(deque::push);
    multiFetchFrom(deque);
  }

  private static void multiFetchFrom(@NonNull final Deque<Object> deque) {

    synchronized (Deque.class) {
      if (deque.isEmpty()) {
        return;
      }
      val counter = new AtomicInteger(Math.min(maxConcurrency, deque.size()));
      val executors = Executors.newFixedThreadPool(counter.get());
      while (counter.get() > 0) {
        executors.submit(() -> {
          val data = deque.pop();

          System.out.println(StrUtil.format("thread is:[{}], data is:[{}], counter:[{}]",
                  Thread.currentThread().getName(),
                  data,
                  counter.decrementAndGet()
          ));

          try {
            //mock different cost time for handle business
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(500));
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

        });
      }

      if (!deque.isEmpty()) {
        multiFetchFrom(deque);
      }

      if (!executors.isShutdown()) {
        executors.shutdown();
      }
    }

  }

  private static final int maxConcurrency = 3;

}
