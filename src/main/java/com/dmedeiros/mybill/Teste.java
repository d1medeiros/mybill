package com.dmedeiros.mybill;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dmedeiros.mybill.bill.model.*;
import com.dmedeiros.mybill.user.model.User;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Teste {

    public static void main(String[] args) throws Exception {


//        bill
//        private Long id;
//        private String name;
//        private Double price;
//        private BillType billType;
//        private Wallet wallet;

//        private LocalDate payday;

//        schedule
//        private Long id;
//        private String name;
//        private Double price;
//        private BillType billType;
//        private Wallet wallet;

//        private int dayToPay;
//        private boolean isMonthly;

        Schedule schedule = new Schedule();
        schedule.setId(1l);
        schedule.setName("diego");
        schedule.setPrice(22.0);
        schedule.setBillType(BillType.GASTOS);
        schedule.setWallet(new Wallet());

        schedule.setDayToPay(12);
        schedule.setMonthly(true);

        Bill bill = schedule;

        System.out.println(bill);




    }


}

