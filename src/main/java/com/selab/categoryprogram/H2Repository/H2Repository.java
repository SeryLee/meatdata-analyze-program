package com.selab.categoryprogram.H2Repository;

import com.selab.categoryprogram.H2Schema.CDMSVO;
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

    @Query("select c.comis_id from CDMS c")
    List<String> findCOMISId();
}
