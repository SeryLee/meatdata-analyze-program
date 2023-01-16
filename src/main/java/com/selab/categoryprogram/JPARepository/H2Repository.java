package com.selab.categoryprogram.JPARepository;

import com.selab.categoryprogram.COMISSchema.CDMSVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2Repository extends JpaRepository<CDMSVO, Long> {
}
