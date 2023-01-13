package com.selab.categoryprogram.MongDBRepository;

import com.selab.categoryprogram.Schema.COMISDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface COMISRepository extends MongoRepository<COMISDoc, String> {
    @Override
    List<COMISDoc> findAllById(Iterable<String> strings);

    @Query("{'_id' : :#{#id}}, {'header.meta_info.product_info.product_id' : true}")
    COMISDoc getProductIdById(@Param("id") String id);

}
