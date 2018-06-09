package com.dmedeiros.mybill.bill.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "billType")
@XmlEnum
public enum BillType {



    GASTOS,
    GANHO,
    DEFAULT


}

