package com.dmedeiros.mybill.bill.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "billType")
@XmlEnum
public enum BillType {

    GASTOS_NORMAL(BillGroup.NORMAL, BillAction.GASTOS),
    GANHO_NORMAL(BillGroup.NORMAL, BillAction.GANHO),
    GASTOS_PLANEJADO(BillGroup.PLANEJADO, BillAction.GASTOS),
    GANHO_PLANEJADO(BillGroup.PLANEJADO, BillAction.GANHO),
    DEFAULT(BillGroup.DEFAULT, BillAction.DEFAULT);


    private BillGroup group;
    private BillAction action;

    BillType(BillGroup group, BillAction action) {
        this.group = group;
        this.action = action;
    }

    public boolean isInGroup(BillGroup group) {
        return this.group == group;
    }

    public boolean isInAction(BillAction action) {
        return this.action == action;
    }

    public BillAction getAction() {
        return this.action;
    }

    public BillGroup getGroup() {
        return this.group;
    }

    public static BillType buildBillType(BillGroup group, BillAction action) {

        if (group == BillGroup.NORMAL && action == BillAction.GASTOS) {
            return GASTOS_NORMAL;
        } else if (group == BillGroup.NORMAL && action == BillAction.GANHO) {
            return GANHO_NORMAL;
        } else if (group == BillGroup.PLANEJADO && action == BillAction.GASTOS) {
            return GASTOS_PLANEJADO;
        } else if (group == BillGroup.PLANEJADO && action == BillAction.GANHO) {
            return GANHO_PLANEJADO;
        } else {
            return DEFAULT;
        }

    }

    public static BillType getTipoConta(String type) {
        if (type.toUpperCase() == BillType.GASTOS_NORMAL.name().toUpperCase()) {
            return BillType.GASTOS_NORMAL;
        } else if (type.toUpperCase() == BillType.GANHO_NORMAL.name().toUpperCase()) {
            return BillType.GANHO_NORMAL;
        } else if (type.toUpperCase() == BillType.GASTOS_PLANEJADO.name().toUpperCase()) {
            return BillType.GASTOS_PLANEJADO;
        } else if (type.toUpperCase() == BillType.GANHO_PLANEJADO.name().toUpperCase()) {
            return BillType.GANHO_PLANEJADO;
        } else {
            return BillType.DEFAULT;
        }

    }
}

