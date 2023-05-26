package com.mycom.poc.philosopher;

import com.mycom.poc.philosopher.service.RoundTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class PhilosopherApplication implements CommandLineRunner {

  @Autowired
  RoundTableService roundTableService;

  public static void main(String[] args) {
    SpringApplication.run(PhilosopherApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    roundTableService.startDinner();
  }
}
