package com.learning.procjson.shared.dto;

import com.learning.procjson.shared.model.Message;

import java.util.Date;

public record MessageRecord(
        Long id,
        MessageRecord producerMessage,
        String status,
        String message
) {}
