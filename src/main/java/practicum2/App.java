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
