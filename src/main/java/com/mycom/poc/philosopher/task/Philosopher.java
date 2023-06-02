package com.mycom.poc.philosopher.task;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Getter
public class Philosopher implements Runnable {

  private int id;
  private boolean isRunning;
  private ReentrantLock left;
  private ReentrantLock right;
  private TimeUnit seconds;

  @SneakyThrows
  @Override
  public void run() {
    while (isRunning) {
      log.info("Checking - Philosopher:{} print thread:{}", id, Thread.currentThread().getId());
      boolean isLeftAcquired = left.tryLock(1, TimeUnit.SECONDS);
      boolean isRightAcquired = false;
      if (isLeftAcquired) {
        isRightAcquired = right.tryLock(1, TimeUnit.SECONDS);
        if (isRightAcquired) {
          eating(2L);
          right.unlock();
          left.unlock();
        } else {
          left.unlock();
          thinking(1L);
        }
      }
    }
  }

  public synchronized void thinking(Long timeToThink) throws InterruptedException {
    log.info("Thinking - Philosopher:{} print thread:{}", id, Thread.currentThread().getId());
    log.info("Thinking - Philosopher:{} start thinking", id);
    seconds.sleep(timeToThink);
    log.info("Thinking - Philosopher:{} end thinking", id);
  }

  public void eating(Long timeToEat) throws InterruptedException {
    log.info("Eating - Philosopher:{} print thread:{}", id, Thread.currentThread().getId());
    log.info("Eating - Philosopher:{} start eating", id);
    seconds.sleep(timeToEat);
    log.info("Eating - Philosopher:{} end eating", id);
  }
}
