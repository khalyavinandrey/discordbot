package com.example.discordbot.handler;

import net.dv8tion.jda.api.events.GenericEvent;

public interface EventHandler<T extends GenericEvent> {

    void handle(T event);
}
