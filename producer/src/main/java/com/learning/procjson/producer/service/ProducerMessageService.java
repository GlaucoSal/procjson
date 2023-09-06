package com.learning.procjson.producer.service;

import com.learning.procjson.producer.repository.ProducerMessageRepository;
import com.learning.procjson.shared.dto.MessageRecord;
import com.learning.procjson.shared.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProducerMessageService {

    private final ProducerMessageRepository messageRepository;

    public ProducerMessageService(ProducerMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageRecord save(MessageRecord messageRecord) {
        Message message = convertRecordToEntity(messageRecord);
        message = messageRepository.save(message);
        return convertEntityToRecord(message);
    }

    public List<MessageRecord> saveAll(List<MessageRecord> messageRecordList) {
        List<Message> messageList = messageRecordList.stream()
                .map(this::convertRecordToEntity)
                .collect(Collectors.toList());
        messageList = messageRepository.saveAll(messageList);
        return messageList.stream()
                .map(this::convertEntityToRecord)
                .collect(Collectors.toList());
    }

    private MessageRecord convertEntityToRecord(Message message) {
        if (message == null)
            return null;

        return new MessageRecord(
                message.getId(),
                convertEntityToRecord(message.getProducerMessage()),
                message.getStatus(),
                message.getMessage()
        );
    }

    private Message convertRecordToEntity(MessageRecord messageRecord) {
        if (messageRecord == null)
            return null;

        Message message = new Message();
        message.setId(messageRecord.id());
        message.setProducerMessage(convertRecordToEntity(messageRecord.producerMessage()));
        message.setStatus(messageRecord.status());
        message.setMessage(messageRecord.message());
        return message;
    }
}
