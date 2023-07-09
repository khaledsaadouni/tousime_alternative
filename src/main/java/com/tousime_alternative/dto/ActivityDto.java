package com.tousime_alternative.dto;

import com.tousime_alternative.model.Activity;
import lombok.Builder;
import lombok.Data;

import java.sql.Time;

@Data
@Builder
public class ActivityDto {
    private long id;
    private String name;
    private Time start;
    private Time end;

    public static ActivityDto fromEntity(Activity activity) {
        if (activity == null) {
            return null;
        }

        return ActivityDto.builder()
                .id(activity.getId())
                .name(activity.getName())
                .start(activity.getStart())
                .end(activity.getEnd())
                .build();
    }

    public static Activity toEntity(ActivityDto dto) {
        if (dto == null) {
            return null;
        }
        Activity activity = new Activity();
        activity.setId(dto.getId());
        activity.setName(dto.getName());
        activity.setStart(dto.getStart());
        activity.setEnd(dto.getEnd());
        return activity;
    }
}
