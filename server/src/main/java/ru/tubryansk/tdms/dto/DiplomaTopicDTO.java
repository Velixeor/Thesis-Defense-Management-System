package ru.tubryansk.tdms.dto;


import lombok.Builder;
import ru.tubryansk.tdms.entity.DiplomaTopic;


@Builder
public record DiplomaTopicDTO(Integer id, String name) {
    public static DiplomaTopicDTO fromEntity(DiplomaTopic diplomaTopic) {
        return DiplomaTopicDTO.builder()
                .id(diplomaTopic.getId())
                .name(diplomaTopic.getName())
                .build();
    }
}
