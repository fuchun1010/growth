package com.tank.renew.my20210405;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.val;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;

/**
 * @author tank198435163.com
 */
public abstract class IncrementerDef {

  @SneakyThrows
  public void increment() {
    val loop = 1 << 25;
    val threadNum = 10;
    counter = 0;
    val countDownLatch = new CountDownLatch(threadNum);
    val start = Instant.now();
    for (int i = 0; i < threadNum; i++) {
      val thread = new Thread(() -> {
        for (int j = 0; j < loop; j++) {
          request();
        }
        countDownLatch.countDown();
      });
      thread.start();
    }

    countDownLatch.await();
    val end = Instant.now();
    val cost = Duration.between(start, end).toMillis();
    System.out.println(StrUtil.format("cost:[{}] ,result:[{}]", cost, counter));
  }

  protected abstract void request();

  @Getter
  public volatile int counter = 0;
}
