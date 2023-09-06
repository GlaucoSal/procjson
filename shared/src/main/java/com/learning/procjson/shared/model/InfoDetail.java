package com.learning.procjson.shared.model;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "info_detail")
public class InfoDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_head", referencedColumnName = "id")
    private InfoHead infoHead;

    @Column(name = "item_number", nullable = false)
    private Integer itemNumber;

    @Column(name = "item_description", length = 250)
    private String itemDescription;

    @Column(name = "item_value", precision = 10, scale = 2)
    private BigDecimal itemValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InfoHead getInfoHead() {
        return infoHead;
    }

    public void setInfoHead(InfoHead infoHead) {
        this.infoHead = infoHead;
    }

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public BigDecimal getItemValue() {
        return itemValue;
    }

    public void setItemValue(BigDecimal itemValue) {
        this.itemValue = itemValue;
    }

}

