package com.rodriguezblanco.priceservice.support.user.bus;

import com.rodriguezblanco.priceservice.support.application.query.Query;
import com.rodriguezblanco.priceservice.support.application.userports.QueryBus;

public class QueryBusImpl extends AbstractBus<Query> implements QueryBus<Query> {
    @Override
    public Object query(Query query) {
        return this.publish(query);
    }
}
