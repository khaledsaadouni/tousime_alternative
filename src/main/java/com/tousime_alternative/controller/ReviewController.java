package com.tousime_alternative.controller;

import com.tousime_alternative.dto.ReviewDto;
import com.tousime_alternative.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/api/v1/review")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add/{idUser}/{idOffer}")
    public ResponseEntity<ReviewDto> add(@RequestBody ReviewDto dto, @PathVariable("idUser") long idUser, @PathVariable("idOffer") long idOffer) {
        return ResponseEntity.ok(reviewService.createReview(dto, idUser, idOffer));
    }

    @GetMapping("/all/{idOffer}")
    public List<ReviewDto> findAll(@PathVariable("idOffer") long id) {
        return reviewService.findAllByoffer(id);
    }

    @DeleteMapping("/delete/{idreview}")
    public void delete(@PathVariable("idreview") Long id) {
        reviewService.delete(id);
    }
}
