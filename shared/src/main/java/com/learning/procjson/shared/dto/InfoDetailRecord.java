package com.learning.procjson.shared.dto;

import com.learning.procjson.shared.model.InfoHead;

import java.math.BigDecimal;
import java.util.Date;

public record InfoDetailRecord(
        Long id,
        InfoHead infoHead,
        Integer itemNumber,
        String itemDescription,
        BigDecimal itemValue
) {}
