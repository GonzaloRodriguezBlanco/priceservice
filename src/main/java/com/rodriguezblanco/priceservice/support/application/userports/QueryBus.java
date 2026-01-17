package com.rodriguezblanco.priceservice.support.application.userports;

import com.rodriguezblanco.priceservice.support.application.Bus;
import com.rodriguezblanco.priceservice.support.application.query.Query;

public interface QueryBus<T extends Query> extends Bus<T> {
    Object query(T query);
}
