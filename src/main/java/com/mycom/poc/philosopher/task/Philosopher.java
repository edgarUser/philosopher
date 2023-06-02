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
      boolean isLeftAcquired = left.tryLock(1, TimeUnit.SECONDS);
      boolean isRightAcquired = false;
      if (isLeftAcquired) {
        isRightAcquired = right.tryLock(1, TimeUnit.SECONDS);
        if (isRightAcquired) {
          eating();
          right.unlock();
          left.unlock();
        } else {
          left.unlock();
          thinking();
        }
      }
    }
  }

  public synchronized void thinking() throws InterruptedException {
    log.info("Thinking - Philosopher:{} print thread:{}", id, Thread.currentThread().getId());
    log.info("Thinking - Philosopher:{} start thinking", id);
    log.info("Thinking - Philosopher:{} end thinking", id);
  }

  public void eating() throws InterruptedException {
    log.info("Eating - Philosopher:{} print thread:{}", id, Thread.currentThread().getId());
    log.info("Eating - Philosopher:{} start eating", id);
    log.info("Eating - Philosopher:{} end eating", id);
  }
}
