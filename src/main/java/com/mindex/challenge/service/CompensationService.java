package com.mindex.challenge.service;
import com.mindex.challenge.data.Compensation;

/**
 * an interface to represent the REST commands needed for access the comp.
 * components of an employee.
 */
public interface CompensationService {
    Compensation create(Compensation money);
    Compensation read(String id);
}