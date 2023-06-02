package com.mycom.poc.philosopher.service;


import com.mycom.poc.philosopher.task.Philosopher;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoundTableService {

  @Value("${application.philosophersPresent}")
  private int philosophersPresent;
  @Autowired
  private ExecutorService executorService;

  public void startDinner() {

    List<Philosopher> philosophers = new ArrayList<>();
    List<ReentrantLock> chopsticks = new ArrayList<>();

    IntStream.range(0, philosophersPresent).forEach(i -> chopsticks.add(new ReentrantLock()));
    log.info("Number of chopsticks : {}", chopsticks.size());

    IntStream.range(0, philosophersPresent)
        .forEach(i -> philosophers.add(new Philosopher(i, true, chopsticks.get(i),
            chopsticks.get((i + 1) % philosophersPresent), TimeUnit.SECONDS)));

    log.info("Number of philosophers : {}", philosophers.size());
    philosophers.forEach(
        philosopher -> log.info("philosopher:{}, left:{} and right:{}", philosopher.getId(),
            philosopher.getLeft(), philosopher.getRight()));
    philosophers.forEach(philosopher -> executorService.submit(philosopher));

  }
}
