package com.learning.procjson.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.procjson.shared.dto.MessageRecord;
import com.learning.procjson.shared.dto.MessageStatus;
import com.learning.procjson.shared.model.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ProducerMessageService producerMessageService;
    private final ObjectMapper objectMapper;


    public KafkaProducerService(ProducerMessageService producerMessageService, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.producerMessageService = producerMessageService;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(String topic, String message) {
        MessageRecord msg = new MessageRecord(null,null, MessageStatus.SENDED, message);
        msg = producerMessageService.save(msg);
        kafkaTemplate.setMessageConverter(new StringJsonMessageConverter());
        try {
            kafkaTemplate.send(topic, objectMapper.writeValueAsString(msg));
        } catch (JsonProcessingException je) {
            throw new RuntimeException(je);
        }
    }
}