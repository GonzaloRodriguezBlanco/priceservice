package com.rodriguezblanco.priceservice.support.user.bus;

import com.rodriguezblanco.priceservice.support.application.Bus;
import com.rodriguezblanco.priceservice.support.application.Handler;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBus<T> implements Bus<T> {
    private final Map<String, Handler<T, ?>> handlers = new HashMap<>();

    public void register(Handler<T, ?> handler) {
        this.handlers.put(getMessageType(handler), handler);
    }

    private String getMessageType(Handler<T, ?> handler) {
        return ((Class<?>) ((ParameterizedType) handler.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0]).getTypeName();
    }

    protected Object publish(T message) {
        String typeName = message.getClass().getTypeName();
        Handler<T, ?> handler = this.handlers.get(typeName);

        if (null == handler) {
            throw NoHandlerForMessage.ofType(typeName);
        }

        return handler.handle(message);
    }
}
