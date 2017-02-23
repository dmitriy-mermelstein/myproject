package com.f5.exersice.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.f5.exersice.model.Median;

public interface MedianRepository extends MongoRepository<Median, String> {

    public List<Median> findByPublisherId(String publisherId);
}