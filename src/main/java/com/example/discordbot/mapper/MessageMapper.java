package com.example.discordbot.mapper;

import com.example.discordbot.dto.MessageDto;
import com.example.discordbot.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {

    MessageDto toDto(Message message);

    Message toEntity(MessageDto messageDto);
}
