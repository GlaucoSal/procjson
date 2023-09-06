package com.learning.procjson.shared.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "info_head")
public class InfoHead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "infoHead", cascade = CascadeType.ALL)
    private List<InfoDetail> infoDetails;

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "items")
    private Integer items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<InfoDetail> getInfoDetails() {
        return infoDetails;
    }

    public void setInfoDetails(List<InfoDetail> infoDetails) {
        this.infoDetails = infoDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getItems() {
        return items;
    }

    public void setItems(Integer items) {
        this.items = items;
    }
}
