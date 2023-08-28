package com.example.discordbot.config.bot;


import com.example.discordbot.listener.MessageEventListener;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;

@Configuration
@RequiredArgsConstructor
public class PongBotConfig {

    @Value("${bot.token}")
    private String token;

    @Bean
    public JDA jda(MessageEventListener messageEventListener) throws LoginException {
        return JDABuilder.createDefault(token)
                .setActivity(Activity.playing("Ping Pong!"))
                .enableIntents(GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(messageEventListener)
                .build();
    }
}
