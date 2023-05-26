package com.mycom.poc.philosopher.task;

import java.util.concurrent.locks.ReentrantLock;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class Philosopher implements Runnable {

  private int id;
  private boolean isRunning;
  private ReentrantLock left;
  private ReentrantLock right;
  @Override
  @SneakyThrows
  public void run() {
    while (isRunning) {
      log.info("I'm the Philosopher number : {} on thread: {}", id, Thread.currentThread().getId());
      log.info("chopsticks left : {} - chopsticks right : {}", left, right);
      Thread.sleep(1000);
    }
  }
}
