package practicum2.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import practicum2.entity.Employee;
import practicum2.service.EmployeeDao;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    private EmployeeDao employeeDao;
    public EmployeeResource() {this.employeeDao = new EmployeeDao();}

    @GET
    @Path("/{empNo}")
    public Response getEmployeeByEmpNo(@PathParam("empNo") int empNo) {

        try {
            Employee employee = employeeDao.findEmployeeByEmpNo(empNo);
            if (employee != null) {
                return Response.ok(employee).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Employee not found\"}")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Internal server error: " + e.getMessage() + "\"}")
                    .build();
        }
    }
}
