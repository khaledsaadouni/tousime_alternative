package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.ReviewDto;
import com.tousime_alternative.model.Review;
import com.tousime_alternative.repository.OfferRepository;
import com.tousime_alternative.repository.ReviewRepository;
import com.tousime_alternative.repository.UserRepository;
import com.tousime_alternative.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private UserRepository userRepository;
    private OfferRepository offerRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, OfferRepository offerRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
    }

    @Override
    public ReviewDto update(ReviewDto dto) {
        return null;
    }


    @Override
    public List<ReviewDto> findAllByoffer(long id) {
        return reviewRepository.findAllByOfferId(id)
                .stream()
                .map(ReviewDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public ReviewDto createReview(ReviewDto dto, long iduser, long idoffer) {
        Review review = ReviewDto.toEntity(dto);
        review.setUser(userRepository.findById(iduser).orElseThrow());
        review.setOffer(offerRepository.findById(idoffer).orElseThrow());
        return ReviewDto.fromEntity(reviewRepository.save(review));
    }
}
