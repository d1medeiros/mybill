package com.dmedeiros.mybill.bill.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BillTypeManager {

    private Map<BillType, BillTypeModel> model;
    private static BillTypeManager billTypeManager = new BillTypeManager();

    private BillTypeManager() {
        model = new HashMap<>();
        model.put(BillType.GASTOS_NORMAL, new BillTypeModel(BillTypePlan.NORMAL, BillTypeAction.GASTOS));
        model.put(BillType.GANHO_NORMAL, new BillTypeModel(BillTypePlan.NORMAL, BillTypeAction.GANHO));
        model.put(BillType.GASTOS_PLANEJADO, new BillTypeModel(BillTypePlan.PLANEJADO, BillTypeAction.GASTOS));
        model.put(BillType.GANHO_PLANEJADO, new BillTypeModel(BillTypePlan.PLANEJADO, BillTypeAction.GANHO));
        model.put(BillType.DEFAULT, new BillTypeModel(BillTypePlan.DEFAULT, BillTypeAction.DEFAULT));
    }

    public static BillTypeManager getBillTypeManager() {
        return billTypeManager;
    }

    public boolean isBillTypePlan(BillType billType, BillTypePlan plan) {
        return this.model.get(billType).isFrom(plan);
    }

    public boolean isBillTypeAction(BillType billType, BillTypeAction action) {
        return this.model.get(billType).isFrom(action);
    }

    public BillTypeAction getAction(BillType billType) {
        return this.model.get(billType).getAction();
    }

    public BillTypePlan getPlan(BillType billType) {
        return this.model.get(billType).getPlan();
    }

    public static BillType buildBillType(BillTypePlan plan, BillTypeAction action) {

        if (plan == BillTypePlan.NORMAL && action == BillTypeAction.GASTOS) {
            return BillType.GASTOS_NORMAL;
        } else if (plan == BillTypePlan.NORMAL && action == BillTypeAction.GANHO) {
            return BillType.GANHO_NORMAL;
        } else if (plan == BillTypePlan.PLANEJADO && action == BillTypeAction.GASTOS) {
            return BillType.GASTOS_PLANEJADO;
        } else if (plan == BillTypePlan.PLANEJADO && action == BillTypeAction.GANHO) {
            return BillType.GANHO_PLANEJADO;
        } else {
            return BillType.DEFAULT;
        }

    }

}

class BillTypeModel {

    BillTypePlan plan;
    BillTypeAction action;


    BillTypeModel(BillTypePlan plan, BillTypeAction action) {
        this.plan = plan;
        this.action = action;
    }

    public BillTypePlan getPlan() {
        return plan;
    }

    public BillTypeAction getAction() {
        return action;
    }

    boolean isFrom(BillTypePlan plan) {
        return this.plan == plan;
    }

    boolean isFrom(BillTypeAction action) {
        return this.action == action;
    }
}

//

//

//
//    public static BillType getTipoConta(String type) {
//        if (type.toUpperCase() == BillType.GASTOS_NORMAL.name().toUpperCase()) {
//            return BillType.GASTOS_NORMAL;
//        } else if (type.toUpperCase() == BillType.GANHO_NORMAL.name().toUpperCase()) {
//            return BillType.GANHO_NORMAL;
//        } else if (type.toUpperCase() == BillType.GASTOS_PLANEJADO.name().toUpperCase()) {
//            return BillType.GASTOS_PLANEJADO;
//        } else if (type.toUpperCase() == BillType.GANHO_PLANEJADO.name().toUpperCase()) {
//            return BillType.GANHO_PLANEJADO;
//        } else {
//            return BillType.DEFAULT;
//        }
//
//    }


//}
