package com.rodriguezblanco.priceservice.support.application.serverports;

import com.rodriguezblanco.priceservice.support.application.Bus;

public interface MessageBus<T extends Message> extends Bus<T> {
    void dispatch(T message);
}
