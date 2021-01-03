package com.github.vjiki.wildsql.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TerritoryArea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String name;
    private Long areaCode;
    private Long areaSquare;

    private String personName;
    private String phoneNumber;
}
