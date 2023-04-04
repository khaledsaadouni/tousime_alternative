package com.tousime_alternative.dto;

import com.tousime_alternative.model.Review;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ReviewDto {
    private long id;
    private String comment;
    private Integer rate;
    private Instant creationDate;
    private UserDto user;

    public static ReviewDto fromEntity(Review review) {
        if (review == null) {
            return null;
        }

        return ReviewDto.builder()
                .id(review.getId())
                .comment(review.getComment())
                .rate(review.getRate())
                .creationDate(review.getCreationDate())
                .user(UserDto.fromEntity(review.getUser()))
                .build();
    }

    public static Review toEntity(ReviewDto dto) {
        if (dto == null) {
            return null;
        }
        Review review = new Review();
        review.setId(dto.getId());
        review.setComment(dto.getComment());
        review.setRate(dto.getRate());
        return review;
    }
}
