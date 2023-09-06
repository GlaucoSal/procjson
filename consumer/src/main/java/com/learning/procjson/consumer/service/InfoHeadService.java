package com.learning.procjson.consumer.service;
import com.learning.procjson.consumer.repository.InfoDetailRepository;
import com.learning.procjson.consumer.repository.InfoHeadRepository;
import com.learning.procjson.shared.dto.InfoDetailRecord;
import com.learning.procjson.shared.dto.InfoHeadRecord;
import com.learning.procjson.shared.model.InfoDetail;
import com.learning.procjson.shared.model.InfoHead;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InfoHeadService {

    private final InfoHeadRepository infoHeadRepository;
    private final InfoDetailRepository infoDetailRepository;

    public InfoHeadService(InfoHeadRepository infoHeadRepository, InfoDetailRepository infoDetailRepository) {
        this.infoHeadRepository = infoHeadRepository;
        this.infoDetailRepository = infoDetailRepository;
    }

    public InfoHeadRecord save(InfoHeadRecord infoHeadRecord) {

        InfoHead infoHead = convertRecordToEntity(infoHeadRecord);
        infoHead.setInfoDetails(null);
        infoHead = infoHeadRepository.save(infoHead);

        List<InfoDetail> infoDetailList = convertRecordToEntity(infoHeadRecord).getInfoDetails();
        if (infoDetailList == null || infoDetailList.isEmpty())
            return convertEntityToRecord(infoHead);

        infoHead.setInfoDetails(new ArrayList<InfoDetail>());

        for (InfoDetail infoDetail : infoDetailList) {
            infoDetail.setInfoHead(infoHead);
            infoHead.getInfoDetails().add(infoDetailRepository.save(infoDetail));
        }

        return convertEntityToRecord(infoHead);
    }

    public List<InfoHeadRecord> saveAll(List<InfoHeadRecord> infoHeadRecordList) {
        List<InfoHead> infoHeadList = infoHeadRecordList.stream()
                .map(this::convertRecordToEntity)
                .collect(Collectors.toList());
        infoHeadList = infoHeadRepository.saveAll(infoHeadList);
        return infoHeadList.stream()
                .map(this::convertEntityToRecord)
                .collect(Collectors.toList());
    }

    private InfoHeadRecord convertEntityToRecord(InfoHead infoHead) {

        List<InfoDetailRecord> infoDetailList = null;
        if (infoHead != null && infoHead.getInfoDetails() != null && !infoHead.getInfoDetails().isEmpty()) {
            infoDetailList = infoHead.getInfoDetails().stream()
                    .map(this::convertDetailEntityToRecord)
                    .collect(Collectors.toList());
        }

        return new InfoHeadRecord(
                infoHead.getId(),
                infoDetailList,
                infoHead.getName(),
                infoHead.getDate(),
                infoHead.getItems()
        );
    }

    private InfoHead convertRecordToEntity(InfoHeadRecord infoHeadRecord) {
        InfoHead infoHead = new InfoHead();
        infoHead.setId(infoHeadRecord.id());

        if (infoHeadRecord.infoDetails() != null && !infoHeadRecord.infoDetails().isEmpty()) {
            List<InfoDetail> infoDetailList = infoHeadRecord.infoDetails().stream()
                    .map(this::convertDetailRecordToEntity)
                    .collect(Collectors.toList());

            infoHead.setInfoDetails(infoDetailList);
        }

        infoHead.setName(infoHeadRecord.name());
        infoHead.setDate(infoHeadRecord.date());
        infoHead.setItems(infoHeadRecord.items());
        return infoHead;
    }

    private InfoDetailRecord convertDetailEntityToRecord(InfoDetail infoDetail) {
        return new InfoDetailRecord(
                infoDetail.getId(),
                infoDetail.getInfoHead(),
                infoDetail.getItemNumber(),
                infoDetail.getItemDescription(),
                infoDetail.getItemValue()
        );
    }

    private InfoDetail convertDetailRecordToEntity(InfoDetailRecord infoDetailRecord) {
        InfoDetail infoDetail = new InfoDetail();
        infoDetail.setId(infoDetailRecord.id());
        infoDetail.setInfoHead(infoDetailRecord.infoHead());
        infoDetail.setItemNumber(infoDetailRecord.itemNumber());
        infoDetail.setItemDescription(infoDetailRecord.itemDescription());
        infoDetail.setItemValue(infoDetailRecord.itemValue());
        return infoDetail;
    }

}
