package com.mindex.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.CompRepo;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

@Service
public class CompensationImpl implements CompensationService {
    public CompensationImpl(CompRepo compRepository){
        this.compRepository = compRepository;
    }
    private static final Logger LOG = LoggerFactory.getLogger(CompensationImpl.class);

    @Autowired
    private CompRepo compRepository;

    @Override
    public Compensation create(Compensation comp) {
        return compRepository.insert(comp);
    }
    @Override
    public Compensation read(String id) {
        return compRepository.findCompByEmployeeEmployeeId(id);
    }

}