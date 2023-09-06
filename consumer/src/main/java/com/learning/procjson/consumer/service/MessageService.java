package com.learning.procjson.consumer.service;

import com.learning.procjson.consumer.repository.MessageRepository;
import com.learning.procjson.shared.dto.MessageRecord;
import com.learning.procjson.shared.dto.MessageStatus;
import com.learning.procjson.shared.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
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

    public MessageRecord saveConsumerMessage(MessageRecord messageRecord) {

        MessageRecord msgConsumer = new MessageRecord(
                null,
                messageRecord,
                MessageStatus.RECEIVED,
                null
        );

        Message message = convertRecordToEntity(msgConsumer);
        message = messageRepository.save(message);
        return convertEntityToRecord(message);
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
