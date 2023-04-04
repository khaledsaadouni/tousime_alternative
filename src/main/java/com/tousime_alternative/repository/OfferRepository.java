package com.tousime_alternative.repository;


import com.tousime_alternative.model.Offer;
import jakarta.persistence.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    Optional<Offer> findById(Long id);

    @OrderBy("Reviews.rate desc")
    List<Offer> findTop6ByOrderByReviewsDesc();

    List<Offer> findAllByOrderByCreationDateDesc();

    List<Offer> findAllByPartnerId(long id);
}
