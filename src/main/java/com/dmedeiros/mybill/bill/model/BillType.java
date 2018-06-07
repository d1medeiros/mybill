package com.dmedeiros.mybill.bill.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "billType")
@XmlEnum
public enum BillType {

    GASTOS,
    GANHO,
    DEFAULT;


    public static BillType getTipoConta(String type) {
        if(type.toUpperCase() == BillType.GASTOS.name().toUpperCase()) {
            return BillType.GASTOS;
        }else if(type.toUpperCase() == BillType.GANHO.name().toUpperCase()) {
            return BillType.GANHO;
        }else {
            return BillType.DEFAULT;
        }

    }
}

