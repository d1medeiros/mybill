package com.dmedeiros.mybill.bill.service;

import com.dmedeiros.mybill.bill.exception.EmptyException;

public interface ServiceThrowableManager<T> {


    void check(T t) throws EmptyException;


}
