package com.learning.procjson.shared.dto;

import com.learning.procjson.shared.model.InfoDetail;

import java.util.Date;
import java.util.List;

public record InfoHeadRecord(
        Long id,
        List<InfoDetailRecord> infoDetails,
        String name,
        Date date,
        Integer items
) {}

