package com.demo.test.demotest.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "total_sales")
@Data
public class TotalSales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String user;

    @Column
    private Long year;

    @Column
    private Long total;
}
