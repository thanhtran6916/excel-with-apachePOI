package com.example.excelwithapachepoi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Option {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private Integer status;

    @Column(name = "op_order")
    private Integer opOrder;

    @Column(name = "value")
    private Integer value;

    @Column(name = "description")
    private String description;

    @Column(name = "value_portal")
    private String valuePortal;
}
