package com.rodriguezblanco.priceservice.support.application.command;

import com.rodriguezblanco.priceservice.support.application.Handler;

public interface CommandHandler<T extends Command, R> extends Handler<T, R> {
    R handle(T command);
}
