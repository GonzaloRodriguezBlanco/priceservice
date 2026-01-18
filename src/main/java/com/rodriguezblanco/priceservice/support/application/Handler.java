package com.rodriguezblanco.priceservice.support.application;

public interface Handler<T,R> {
    R handle(T message);
}
