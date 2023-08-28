package com.example.discordbot.service;

import com.example.discordbot.dto.MessageDto;
import com.example.discordbot.mapper.MessageMapper;
import com.example.discordbot.model.Message;
import com.example.discordbot.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Transactional
    public MessageDto save(MessageDto dto) {
        Message entity = messageMapper.toEntity(dto);

        Message saved = messageRepository.save(entity);

        log.info("Saved message: {}", saved);

        return messageMapper.toDto(saved);
    }
}
