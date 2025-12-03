package practicum2;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import practicum2.rest.DepartmentResource;
import practicum2.rest.EmployeeResource;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class App extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();
        s.add(EmployeeResource.class);
        s.add(DepartmentResource.class);
        return s;
    }
}

//End point 1: getting all the names and department numbers
//http://localhost:8092/practicum2/api/departments/list

//End point 2: return full employee record given employee number
//http://localhost:8092/practicum2/api/employees/id?empNo=110022

//End point 3: return all employee records by department with pagination
//Example:  http://localhost:8092/practicum2/api/employees/department?deptNo=d001&page=1

//End point 4:  employee promotion
//query fields: (
//      empNo (not null),
//      newTitle,
//      newDeptNo,
//      newManager,
//      newSalary,
//      newStartEndDateStr (in the format of yyyy-mm-dd)
//  )
//http://localhost:8092/promote?
//          empNo=110022
//          &newTitle=head%20chef
//          &newDeptNo=d002
//          &newManager=true
//          &newSalary=123456
//          &newStartEndDateStr=2025-12-12