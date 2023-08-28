package com.example.discordbot.handler.receivedmessagehandler;

import com.example.discordbot.handler.MessageEventHandler;
import com.example.discordbot.service.MessageService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PingMessageHandler extends MessageEventHandler {

    private static final String MESSAGE_TO_RECEIVE = "!ping";
    private static final String MESSAGE_TO_SEND = "!pong";

    public PingMessageHandler(MessageService messageService) {
        super(messageService);
    }

    @Override
    public boolean isApplicable(String message) {
        return message.equals(MESSAGE_TO_RECEIVE);
    }

    @Override
    protected Map.Entry<String, String> handleMessage(MessageReceivedEvent event, String message) {
        return Map.entry(message, MESSAGE_TO_SEND);
    }
}
