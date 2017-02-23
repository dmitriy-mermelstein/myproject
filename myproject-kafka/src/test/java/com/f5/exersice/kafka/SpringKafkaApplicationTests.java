package com.f5.exersice.kafka;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.f5.exersice.kafka.consumer.Receiver;
import com.f5.exersice.kafka.consumer.ReceiverHandler;
import com.f5.exersice.kafka.producer.Sender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringKafkaApplicationTests {

    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;
    
	@Value("${kafka.topics}")
    private String kafkaTopic;

    @Test
    public void testReceiver() throws Exception {
    	ReceiverHandler receiverHandler = new ReceiverHandlerTest();
    	receiver.setHandler(kafkaTopic, receiverHandler);
    	
        sender.sendMessage("f5sensor.data", "Hello, it's sensor data!");
        //sender.sendMessage("f5sensor.data", "Hello, it's sensor test!");

        receiver.getLatch().await(2000, TimeUnit.MILLISECONDS);
        assertThat(receiver.getLatch().getCount()).isEqualTo(0);
    }
    
    //@Test
//    public void testReceiver() throws Exception {
//    	ReceiverHandler receiverHandler = new ReceiverHandlerTest();
//    	receiver.setHandler(receiverHandler);
//    	
//        sender.sendMessage("f5sensor.data", "Hello, it's sensor data!");
//        sender.sendMessage("f5sensor.data", "Hello, it's sensor test!");
//
//        receiver.getLatch().await(2000, TimeUnit.MILLISECONDS);
//        assertThat(receiver.getLatch().getCount()).isEqualTo(0);
//    }
}
