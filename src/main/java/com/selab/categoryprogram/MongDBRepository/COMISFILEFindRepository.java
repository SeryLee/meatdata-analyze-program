package com.selab.categoryprogram.MongDBRepository;

import com.selab.categoryprogram.MongoDBSchema.COMISDoc_file;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface COMISFILEFindRepository extends MongoRepository<COMISDoc_file, String> {
}
