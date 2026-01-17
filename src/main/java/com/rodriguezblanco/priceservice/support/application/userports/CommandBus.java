package com.rodriguezblanco.priceservice.support.application.userports;

import com.rodriguezblanco.priceservice.support.application.Bus;
import com.rodriguezblanco.priceservice.support.application.command.Command;

public interface CommandBus<T extends Command> extends Bus<T> {
    Object dispatch(T command);
}
