package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * essentially a way to allow mongo to have access to id in the same way employee did.
 */
@Repository
public interface CompRepo extends MongoRepository<Compensation, String> {
    Compensation findCompByEmployeeEmployeeId(String employeeId);
}