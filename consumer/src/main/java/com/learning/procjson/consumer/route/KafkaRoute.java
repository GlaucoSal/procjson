package com.learning.procjson.consumer.route;

import com.learning.procjson.shared.dto.InfoHeadRecord;
import com.learning.procjson.shared.dto.MessageRecord;
import com.learning.procjson.shared.model.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class KafkaRoute extends RouteBuilder {


    @Override
    public void configure() {

        from("kafka:testProducer?brokers=localhost:9093")
                .log("Recebendo a mensagem do Producer Kafka: ${body}")
                .to("log:KafkaConsumerRoute")
                .log("Convertendo para classe Message")
                .unmarshal().json(JsonLibrary.Jackson, MessageRecord.class)
                .log("Persistindo mensagem de consumer")
                .to("bean:messageService?method=saveConsumerMessage")
                .log("Convertendo propriedade message no InfoHead")
                //.convertBodyTo(MessageRecord.class)
                .log("Body Atual: ${body}")
                .setBody().simple("${body.producerMessage.message}")
//                .process(exchange -> {
//                    MessageRecord msg = exchange.getIn().getBody(MessageRecord.class);
//                    String str = msg.message();
//                    exchange.getIn().setBody(str);
//                })
                .log("Json do atributo message (InfoHead): ${body}")
                .unmarshal().json(JsonLibrary.Jackson, InfoHeadRecord.class) // Converte a string JSON em um objeto InfoHead
                .log("Presistindo InfoHeah InfoHead: ${body}")
                .to("bean:infoHeadService?method=save")
                .log("fim da rota")
                .to("direct:end");


    }
}
