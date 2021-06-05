package com.example.creditcardapi.repository;

import com.example.creditcardapi.domain.CardDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to do operatios on CardDetail Database
 */
@Repository
public interface CardDetailsRepository extends CrudRepository<CardDetail,String>, PagingAndSortingRepository <CardDetail,String>{

    @Override
    Page<CardDetail> findAll(Pageable pageable);
}
