package com.example.discordbot.listener;

import com.example.discordbot.handler.MessageEventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageEventListener extends ListenerAdapter {

    private final List<MessageEventHandler> handlers;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        log.info(String.format("Handling message %s from channel %s",
                        event.getMessage().getContentRaw(),
                        event.getChannel().getName()));

        handlers.forEach(handler -> handler.handle(event));
    }
}
