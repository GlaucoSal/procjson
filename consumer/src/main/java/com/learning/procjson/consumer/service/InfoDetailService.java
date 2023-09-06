package com.learning.procjson.consumer.service;

import com.learning.procjson.consumer.repository.InfoDetailRepository;
import com.learning.procjson.shared.dto.InfoDetailRecord;
import com.learning.procjson.shared.model.InfoDetail;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InfoDetailService {

    private final InfoDetailRepository infoDetailRepository;

    public InfoDetailService(InfoDetailRepository infoDetailRepository) {
        this.infoDetailRepository = infoDetailRepository;
    }

    public InfoDetailRecord save(InfoDetailRecord infoDetailRecord) {
        InfoDetail infoDetail = convertRecordToEntity(infoDetailRecord);
        infoDetail = infoDetailRepository.save(infoDetail);
        return convertEntityToRecord(infoDetail);
    }

    public List<InfoDetailRecord> saveAll(List<InfoDetailRecord> infoDetailRecordList) {
        List<InfoDetail> infoDetailList = infoDetailRecordList.stream()
                .map(this::convertRecordToEntity)
                .collect(Collectors.toList());
        infoDetailList = infoDetailRepository.saveAll(infoDetailList);
        return infoDetailList.stream()
                .map(this::convertEntityToRecord)
                .collect(Collectors.toList());
    }

    private InfoDetailRecord convertEntityToRecord(InfoDetail infoDetail) {
        return new InfoDetailRecord(
                infoDetail.getId(),
                infoDetail.getInfoHead(),
                infoDetail.getItemNumber(),
                infoDetail.getItemDescription(),
                infoDetail.getItemValue()
        );
    }

    private InfoDetail convertRecordToEntity(InfoDetailRecord infoDetailRecord) {
        InfoDetail infoDetail = new InfoDetail();
        infoDetail.setId(infoDetailRecord.id());
        infoDetail.setInfoHead(infoDetailRecord.infoHead());
        infoDetail.setItemNumber(infoDetailRecord.itemNumber());
        infoDetail.setItemDescription(infoDetailRecord.itemDescription());
        infoDetail.setItemValue(infoDetailRecord.itemValue());
        return infoDetail;
    }
}
