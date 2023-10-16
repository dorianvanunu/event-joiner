package com.assessment.consumer;

import com.assessment.model.event.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
@EnableKafka
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class KafkaMessageConsumerTest {
    @Autowired
    private RegistrationEventConsumer consumer;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    private final CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "topic.a", groupId = "event-processing-group")
    public void listen(RegistrationEvent registrationEvent) {
        latch.countDown();
    }


    @Test
    public void testKafkaMessageConsumer() throws InterruptedException {
        EventKey key = new EventKey("001", "1234");

        RegistrationEventValue registrationEventValue = new RegistrationEventValue(
                "001", "int4123", true, "29525", "11", "int7218", "2023-06-30T18:21:31.000000Z", "REG03814"
        );
        AuditData auditDataRegistration = new AuditData("RGR", "Registration");
        RegistrationEvent registrationEvent = new RegistrationEvent(key, registrationEventValue, auditDataRegistration);

        KafkaTemplate<EventKey, RegistrationEvent> kafkaTemplateRegistration = getRegistrationEventKafkaTemplate();
        ProducerRecord<EventKey, RegistrationEvent> record = new ProducerRecord<>("topic.a", key, registrationEvent);
        kafkaTemplateRegistration.send("topic.a", key, registrationEvent);

        SalesEventValue salesEventValue = new SalesEventValue("29525", "03814", "2", "2023-07-30T18:21:31.000000Z", "001");
        AuditData auditDataSales = new AuditData("SLS", "SalesEvent");
        SalesEvent salesEvent = new SalesEvent(key, salesEventValue, auditDataSales);
        KafkaTemplate<EventKey, SalesEvent> kafkaTemplateSales = getSalesEventKafkaTemplate();
        kafkaTemplateSales.send("topic.b", key, salesEvent);
        latch.await();
    }

    private KafkaTemplate<EventKey, RegistrationEvent> getRegistrationEventKafkaTemplate() {
        Map<String, Object> producerConfig = new HashMap<>();
        producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, embeddedKafkaBroker.getBrokersAsString());
        producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfig));
    }

    private KafkaTemplate<EventKey, SalesEvent> getSalesEventKafkaTemplate() {
        Map<String, Object> producerConfig = new HashMap<>();
        producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, embeddedKafkaBroker.getBrokersAsString());
        producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfig));
    }
}
