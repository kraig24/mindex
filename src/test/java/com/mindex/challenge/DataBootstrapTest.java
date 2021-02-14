package com.mindex.challenge;

import com.mindex.challenge.dao.CompRepo;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.impl.CompensationImpl;
import com.mindex.challenge.service.impl.EmployeeServiceImpl;
import com.mindex.challenge.service.impl.ReportingStructureImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataBootstrapTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompRepo compensationRepository;

    @Test
    public void test() {
        Employee employee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        assertNotNull(employee);
        assertEquals("John", employee.getFirstName());
        assertEquals("Lennon", employee.getLastName());
        assertEquals("Development Manager", employee.getPosition());
        assertEquals("Engineering", employee.getDepartment());
    }

    /**
     * This is a test showing that Lennon does in fact have 4 total employees under him.
     * NOTE: in order to allow this test case to pass, I needed to add a constructor to employeeServiceImpl so that
     * I could share the instance of the mongodb database. The way it was before, the value for the employee repository
     * was autowired, but was not being read in correctly unless initialized outside of the test environment. To correct
     * this, I would suggest the use of the singleton pattern to create a single instance of the database with/without
     * debug mode. This would allow a layer of abstraction for running the script. I didn't want to change the source
     * code, so I felt the constructor was the least invasive solution.
     */
    @Test
    public void testReportingStructure() {
        Employee lennon = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl(employeeRepository);
        ReportingStructureImpl impl = new ReportingStructureImpl(employeeService);
        assertEquals(4, impl.getTotalReports(lennon));
    }

    /**
     * this is a test to ensure leaf nodes that have no children in the JSON tree will also return zero from the
     * recursive traversal.
     */
    @Test
    public void testReportingNullLeaves() {
        Employee McCartney = employeeRepository.findByEmployeeId("b7839309-3348-463b-a7e3-5de1c168beb3");
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl(employeeRepository);
        ReportingStructureImpl impl = new ReportingStructureImpl(employeeService);
        assertEquals(0, impl.getTotalReports(McCartney));

    }

    /**
     * test to verify that a compensation can be created using data from an employee that was retrieved from the
     * database.
     */
    @Test
    public void testCompensation() {
        Employee lennon = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        int salary = 95000;
        String date = "2/12/2021";
        Compensation comp = new Compensation();
        comp.seteffectiveDate(date);
        comp.setEmployee(lennon);
        comp.setSalary(salary);
        CompensationImpl compImpl = new CompensationImpl(compensationRepository);

        compImpl.create(comp);

    }


}