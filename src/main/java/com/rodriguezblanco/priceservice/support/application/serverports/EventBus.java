package com.rodriguezblanco.priceservice.support.application.serverports;

import com.rodriguezblanco.priceservice.support.application.Bus;

public interface EventBus<T extends Event> extends Bus<T> {
    void dispatch(T event);
}
