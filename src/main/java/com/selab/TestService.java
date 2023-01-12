package com.selab;

import com.selab.MongDBRepository.TestRepo;
import com.selab.Schema.TestDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepo testRepo;

    public List<TestDoc> getTest(String id) {
        return testRepo.findAllById(Collections.singleton(id));
    }
}
