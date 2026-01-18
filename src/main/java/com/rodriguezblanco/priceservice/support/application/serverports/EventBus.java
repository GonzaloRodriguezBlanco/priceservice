package com.rodriguezblanco.priceservice.support.application.serverports;

import com.rodriguezblanco.priceservice.support.application.Bus;
import com.rodriguezblanco.priceservice.support.domain.event.Event;

public interface EventBus<T extends Event> extends Bus<T> {
    void dispatch(T event);
}
