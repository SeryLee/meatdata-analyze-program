package com.selab.categoryprogram.MongDBRepository;

import com.selab.categoryprogram.MongoDBSchema.COMISDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FindRepository extends MongoRepository<COMISDoc, String> {

    @Query("{'_id' : :#{#id}}, {'header.meta_info.product_info.product_id' : true}")
    COMISDoc getProductIdById(@Param("id") String id);

    @Query("{$and :[{'_id': {'$regex' : :#{#includeWordInFileName}}}, {'header.meta_info.product_info.product_group':{$all::#{#saveCodeGroup}}}]}")
    List<COMISDoc> findAllByReadCodeDto(String includeWordInFileName, List<String> saveCodeGroup);
}
