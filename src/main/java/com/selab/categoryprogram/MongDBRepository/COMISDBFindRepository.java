package com.selab.categoryprogram.MongDBRepository;

import com.selab.categoryprogram.MongoDBSchema.COMISDoc_db;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface COMISDBFindRepository extends MongoRepository<COMISDoc_db, String> {

    @Query("{'_id' : :#{#id}}")
    COMISDoc_db getProductIdById(@Param("id") String id);

    @Query("{$and :[{'_id': {'$regex' : :#{#includeWordInFileName}}}, {'header.meta_info.product_info.product_group':{$all::#{#saveCodeGroup}}}]}")
    List<COMISDoc_db> findAllByReadCodeDto(String includeWordInFileName, List<String> saveCodeGroup);
}
