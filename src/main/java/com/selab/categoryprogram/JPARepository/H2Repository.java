package com.selab.categoryprogram.JPARepository;

import com.selab.categoryprogram.RDBSchema.CDMSVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface H2Repository extends JpaRepository<CDMSVO, Long> {
    @Override
    long count();

    long countByCategory(String category);
    @Query("select distinct (c.category) from CDMS c")
    List<String> findCategory();

    long countByType(String type);

}
