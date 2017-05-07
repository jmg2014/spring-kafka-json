package com.example.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.example.model.Car;

public class Producer {

  private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

  @Value("${topic.json}")
  private String jsonTopic;

  @Autowired
  private KafkaTemplate<String, Car> kafkaTemplate;

  public void send(Car car) {
    LOGGER.info("sending car='{}'", car);
    kafkaTemplate.send(jsonTopic, car);
  }

  public void send(String topic, Car message) {
    // the KafkaTemplate provides asynchronous send methods returning a Future
    // if we want to keep the order in the messages, we only need to add the 'Key'
    // parameter in send method
    ListenableFuture<SendResult<String, Car>> future = kafkaTemplate.send(topic, message);

    // register a callback with the listener to receive the result of the send asynchronously
    future.addCallback(new ListenableFutureCallback<SendResult<String, Car>>() {

      @Override
      public void onSuccess(SendResult<String, Car> result) {
        LOGGER.info("sent message='{}' with offset={}", message, result.getRecordMetadata().offset());
      }

      @Override
      public void onFailure(Throwable ex) {
        LOGGER.error("unable to send message='{}'", message, ex);
      }
    });

    // or, to block the sending thread to await the result, invoke the future's get() method
  }
}
