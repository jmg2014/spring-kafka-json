package com.example.kafka.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafka.producer.Producer;
import com.example.model.Car;

@RestController
@RequestMapping(path = "/kafka-producer")
public class KafkaProducerController {


  private Producer producer;

  @Autowired
  public void setSender(Producer producer) {
    this.producer = producer;
  }

  @PostMapping(value = "/add")
  public ResponseEntity<Car> addCar(@RequestBody Car car) {

    producer.send("car.topic", car);

    return new ResponseEntity<>(car, HttpStatus.OK);
  }


}
