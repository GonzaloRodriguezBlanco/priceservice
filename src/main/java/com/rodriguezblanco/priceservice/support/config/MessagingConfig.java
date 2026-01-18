package com.rodriguezblanco.priceservice.support.config;

import com.rodriguezblanco.priceservice.support.application.command.Command;
import com.rodriguezblanco.priceservice.support.application.command.CommandHandler;
import com.rodriguezblanco.priceservice.support.application.query.Query;
import com.rodriguezblanco.priceservice.support.application.query.QueryHandler;
import com.rodriguezblanco.priceservice.support.application.userports.CommandBus;
import com.rodriguezblanco.priceservice.support.application.userports.QueryBus;
import com.rodriguezblanco.priceservice.support.user.bus.CommandBusImpl;
import com.rodriguezblanco.priceservice.support.user.bus.QueryBusImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
public class MessagingConfig {
    private final ApplicationContext applicationContext;

    public MessagingConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    CommandBus<Command> commandBus() {
        Collection<CommandHandler> handlers = applicationContext.getBeansOfType(CommandHandler.class).values();
        CommandBus<Command> commandBus = new CommandBusImpl();
        handlers.forEach(commandBus::register);
        return commandBus;
    }

    @Bean
    QueryBus<Query> queryBus() {
        Collection<QueryHandler> handlers = applicationContext.getBeansOfType(QueryHandler.class).values();
        QueryBus<Query> queryBus = new QueryBusImpl();
        handlers.forEach(queryBus::register);
        return queryBus;
    }
}
