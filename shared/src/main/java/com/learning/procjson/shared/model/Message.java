package com.learning.procjson.shared.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_producer", referencedColumnName = "id")
    private Message producerMessage;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    public Message() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Message getProducerMessage() {
        return producerMessage;
    }

    public void setProducerMessage(Message producerMessage) {
        this.producerMessage = producerMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
