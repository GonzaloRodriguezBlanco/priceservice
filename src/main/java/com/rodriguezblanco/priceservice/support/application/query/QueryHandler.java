package com.rodriguezblanco.priceservice.support.application.query;

import com.rodriguezblanco.priceservice.support.application.Handler;

public interface QueryHandler<T extends Query, R> extends Handler<T, R> {
    R handle(T query);
}
