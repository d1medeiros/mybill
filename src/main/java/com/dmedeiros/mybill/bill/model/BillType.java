package com.dmedeiros.mybill.bill.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "billType")
@XmlEnum
public enum BillType {

    GASTOS_NORMAL,
    GANHO_NORMAL,
    GASTOS_PLANEJADO,
    GANHO_PLANEJADO,
    DEFAULT;


    public static BillType getTipoConta(String type) {
        if(type.toUpperCase() == BillType.GASTOS_NORMAL.name().toUpperCase()) {
            return BillType.GASTOS_NORMAL;
        }else if(type.toUpperCase() == BillType.GANHO_NORMAL.name().toUpperCase()) {
            return BillType.GANHO_NORMAL;
        }else if(type.toUpperCase() == BillType.GASTOS_PLANEJADO.name().toUpperCase()) {
            return BillType.GASTOS_PLANEJADO;
        }else if(type.toUpperCase() == BillType.GANHO_PLANEJADO.name().toUpperCase()) {
            return BillType.GANHO_PLANEJADO;
        }else {
            return BillType.DEFAULT;
        }

    }
}

