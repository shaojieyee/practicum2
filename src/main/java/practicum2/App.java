/**********************************************************************************
*
* From: https://www.baeldung.com/jax-rs-spec-and-implementations
* Edited By: Angeline Tan
* Edited On: 20 November 2021
* Description: RESTful web service that has a single TestMessage object as
*              a resource.
*
**********************************************************************************/


package practicum2;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import practicum2.rest.EmployeeResource;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class App extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(EmployeeResource.class);
        return s;
    }
}
