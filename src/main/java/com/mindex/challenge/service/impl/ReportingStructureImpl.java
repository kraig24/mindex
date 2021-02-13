package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureImpl {

    @Autowired
    private EmployeeService employeeService;
    public ReportingStructureImpl(EmployeeService newEmployeeService) {
        employeeService = newEmployeeService;
    }

    /**
     * gets the reporting-structure for the current is that we are looking at.
     * employee is determined by reading in the string id from REST calls using this id.
     * @param id - the string value associated with the particular employee in question
     * @return repStruct - the value received when finding all child values under that employee node.
     */
    public ReportingStructure getReportingStructure(String id) {
        Employee currentEmployee = employeeService.read(id);
        ReportingStructure repStruct = new ReportingStructure();
        repStruct.setEmployee(currentEmployee);
        repStruct.setNumberOfReports(getTotalReports(currentEmployee));
        return repStruct;

    }

    /**
     * uses recursive call and Depth-First- Search mentality to call all "children" of employee tree
     * @param currentEmployee - the current employee we are looking at in the employee tree
     * @return count - integer representing the total amount of direct reports.
     */
    public int getTotalReports(Employee currentEmployee) {
        int count = 0;
        if (currentEmployee.getDirectReports() == null) {
            return count;
        }
        else {
            count += currentEmployee.getDirectReports().size();
            for (Employee children : currentEmployee.getDirectReports()) {
                count += getTotalReports(employeeService.read(children.getEmployeeId()));
            }
            return count;
        }
    }

}