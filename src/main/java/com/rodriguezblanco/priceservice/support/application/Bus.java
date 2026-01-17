package com.rodriguezblanco.priceservice.support.application;

public interface Bus<T> {
    void register(Handler<T,?> handler);
}
