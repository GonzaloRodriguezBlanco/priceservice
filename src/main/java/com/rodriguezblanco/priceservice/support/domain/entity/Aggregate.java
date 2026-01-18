package com.rodriguezblanco.priceservice.support.domain.entity;

import com.rodriguezblanco.priceservice.support.domain.event.Event;

import java.util.Collection;

public abstract class Aggregate {
    private Collection<Event> events;

    protected void collect(Event event) {
        this.events.add(event);
    }

    protected Collection<Event> events() {
        return events;
    }

    protected void flush() {
        this.events.clear();
    }
}
