package com.dmedeiros.mybill.bill.exception;

public class WalletEmptyException extends RuntimeException {
    public WalletEmptyException() {
        super("wallet was not filled.");
    }
}
