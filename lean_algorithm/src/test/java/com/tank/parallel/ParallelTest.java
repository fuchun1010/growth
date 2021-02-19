package com.tank.parallel;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ParallelTest {

  @Test
  void testRun() throws InterruptedException, TimeoutException, BrokenBarrierException {
    val counter = new AtomicInteger(0);
    val cyclicBarrier = new CyclicBarrier(5, () -> {
      System.out.println(counter.incrementAndGet());
    });
    cyclicBarrier.await(2, TimeUnit.SECONDS);
    Assertions.assertEquals(5, counter.get());
    TimeUnit.SECONDS.sleep(1);
  }

}
