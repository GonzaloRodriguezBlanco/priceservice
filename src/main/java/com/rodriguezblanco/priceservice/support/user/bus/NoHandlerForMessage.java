package com.rodriguezblanco.priceservice.support.user.bus;

public class NoHandlerForMessage extends RuntimeException {
    private static final String MSG_OF_TYPE = "No handler found for type %s";
    private NoHandlerForMessage(String message) {
        super(message);
    }

    public static NoHandlerForMessage ofType(String typeName) {
        return new NoHandlerForMessage(MSG_OF_TYPE.formatted(typeName));
    }
}
