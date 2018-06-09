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
    DEFAULT


}

