package com.tousime_alternative.repository;

import com.tousime_alternative.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByOfferId(long id);
}
