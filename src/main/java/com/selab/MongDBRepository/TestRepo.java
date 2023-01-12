package com.selab.MongDBRepository;

import com.selab.Schema.TestDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepo extends MongoRepository<TestDoc, String> {
    @Override
    List<TestDoc> findAllById(Iterable<String> strings);
}
