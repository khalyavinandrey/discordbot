package com.example.discordbot.handler;

import com.example.discordbot.dto.MessageDto;
import com.example.discordbot.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public abstract class MessageEventHandler implements EventHandler<MessageReceivedEvent> {

    private final MessageService messageService;

    protected abstract boolean isApplicable(String message);

    protected abstract Map.Entry<String, String> handleMessage(MessageReceivedEvent event, String message);

    @Override
    @Async("messageEventHandlerExecutor")
    public void handle(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();

        if (isApplicable(message)) {
            Map.Entry<String, String> handledMessage = handleMessage(event, message);

            log.info(String.format("Handled message %s from channel %s", message, event.getChannel().getName()));

            sendMessageToChannel(event, handledMessage.getValue());

            saveToDB(handledMessage.getKey());
        }
    }

    protected MessageDto saveToDB(String message) {

        return messageService.save(
                MessageDto.builder()
                        .message(message)
                        .build());
    }

    protected void sendMessageToChannel(MessageReceivedEvent event, String message) {
        event.getChannel()
                .sendMessage(message)
                .queue();

        log.info(String.format("Message %s was sent to channel %s", message, event.getChannel().getName()));
    }
}
