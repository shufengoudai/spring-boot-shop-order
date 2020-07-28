package com.ensat.entities;

import javax.persistence.*;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "shopName")
    private String shopName;

    @Column(name = "bossLastName")
    private String bossLastName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getBossLastName() {
        return bossLastName;
    }

    public void setBossLastName(String bossLastName) {
        this.bossLastName = bossLastName;
    }
}
