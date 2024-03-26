package org.databaserepo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.databaserepo.database.DatabaseRepositoryImplementation;
import org.databaserepo.database.StorageTarget;
import org.databaserepo.entity.Employee;
import org.databaserepo.entity.EmployeeAddress;
import org.databaserepo.entity.EmployeeDetails;
import org.databaserepo.interfaces.EmployeeInputDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AppTest {
    @Mock
    private StorageTarget mockStorageTarget;
    @Mock
    private EmployeeInputDetails employeeInputDetails;
    @Mock
    private DatabaseRepositoryImplementation mockdatabaseRepositoryImplementation;

    @Test
    public void testFindById(){
        String employeeId="123";
        Employee employee1=new Employee(new EmployeeDetails("Akshira","123","akshira@gmail.com",8105701279L),new EmployeeAddress("shakthi","kallya","karkala","Karnataka",574110),new EmployeeAddress("akshaya","global","rrnagar","karnataka",574111));
//        when(employeeInputDetails.displayBasedOnEmployeeId(employeeId)).thenReturn(employee1);
//        Employee actual=mockdatabaseRepositoryImplementation.displayBasedOnEmployeeId(employeeId);
//        assertSame(employee1,actual);
        when(mockdatabaseRepositoryImplementation.displayBasedOnEmployeeId(employeeId).getEmployeeDetails().getEmployeeId()).thenReturn(employeeId);
        assertSame(employeeId,employee1.getEmployeeDetails().getEmployeeId());
    }
}

