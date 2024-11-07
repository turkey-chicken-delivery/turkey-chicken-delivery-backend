package com.sparta.i_am_delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class IAmDeliveryApplication {

  public static void main(String[] args) {
    SpringApplication.run(IAmDeliveryApplication.class, args);
  }
}
