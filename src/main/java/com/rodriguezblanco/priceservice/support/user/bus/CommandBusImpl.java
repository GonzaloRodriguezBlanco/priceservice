package com.rodriguezblanco.priceservice.support.user.bus;

import com.rodriguezblanco.priceservice.support.application.command.Command;
import com.rodriguezblanco.priceservice.support.application.userports.CommandBus;

public class CommandBusImpl extends AbstractBus<Command> implements CommandBus<Command> {
    @Override
    public Object dispatch(Command command) {
        return this.publish(command);
    }
}
