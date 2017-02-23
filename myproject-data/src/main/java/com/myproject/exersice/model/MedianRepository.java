package com.myproject.exersice.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.myproject.exersice.model.Median;

public interface MedianRepository extends MongoRepository<Median, String> {

    public List<Median> findByPublisherId(String publisherId);
}