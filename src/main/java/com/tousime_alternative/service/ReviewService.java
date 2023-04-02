package com.tousime_alternative.service;

import com.tousime_alternative.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto update(ReviewDto dto);

    List<ReviewDto> findAllByoffer(long id);

    void delete(Long id);

    ReviewDto createReview(ReviewDto dto, long iduser, long idoffer);
}
