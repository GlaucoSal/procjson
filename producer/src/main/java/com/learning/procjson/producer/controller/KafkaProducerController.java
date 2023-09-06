package com.learning.procjson.producer.controller;

import com.learning.procjson.producer.service.KafkaProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {

    private final KafkaProducerService kafkaProducerService;

    public KafkaProducerController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/enviar-mensagem")
    public void enviarMensagem(@RequestBody String mensagem) {
        kafkaProducerService.sendMessage("testProducer", mensagem);
    }
}
