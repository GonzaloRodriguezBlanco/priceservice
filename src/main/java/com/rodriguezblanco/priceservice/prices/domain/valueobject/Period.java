package com.rodriguezblanco.priceservice.prices.domain.valueobject;

import java.time.LocalDateTime;

public class Period {
    private final LocalDateTime from;
    private final LocalDateTime to;

    private Period(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    public LocalDateTime from() {
        return from;
    }

    public LocalDateTime to() {
        return to;
    }

    public static Period of(LocalDateTime from, LocalDateTime to) {
        return new Period(from, to);
    }
}
