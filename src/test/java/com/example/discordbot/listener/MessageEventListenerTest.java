package com.example.discordbot.listener;

import com.example.discordbot.handler.MessageEventHandler;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageEventListenerTest {

    @Spy
    private List<MessageEventHandler> handlers = new ArrayList<>();

    @Mock
    private MessageReceivedEvent event;

    @Mock
    private Message message;

    @Mock
    private User user;

    @Mock
    private MessageChannel messageChannel;

    @InjectMocks
    private MessageEventListener messageEventListener;

    @Test
    void testOnMessageReceived_AuthorIsUser_ShouldHandle() {
        when(event.getAuthor()).thenReturn(user);
        when(user.isBot()).thenReturn(false);
        when(event.getMessage()).thenReturn(message);
        when(event.getChannel()).thenReturn(messageChannel);
        when(message.getContentRaw()).thenReturn("Test message");

        MessageEventHandler mockHandler = mock(MessageEventHandler.class);
        MessageEventHandler mockHandler1 = mock(MessageEventHandler.class);

        handlers.add(mockHandler);
        handlers.add(mockHandler1);

        messageEventListener.onMessageReceived(event);

        verify(mockHandler).handle(event);
        verify(mockHandler1).handle(event);
    }

    @Test
    void testOnMessageReceived_AuthorIsBot_ShouldNotHandle() {
        when(event.getAuthor()).thenReturn(user);
        when(user.isBot()).thenReturn(true);

        messageEventListener.onMessageReceived(event);

        verifyNoInteractions(handlers);
    }
}