package practicum2.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import practicum2.entity.Department;
import practicum2.service.DepartmentDao;

import java.util.List;

@Path("/departments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartmentResource {

    private DepartmentDao departmentDao;
    public DepartmentResource() {this.departmentDao = new DepartmentDao();}

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Department service online").build();
    }

    @GET
    @Path("/list")
    public Response getDistinctDepartments() {
        try {
            List<Department> department = departmentDao.findAllUniqueDepartment();
            if (department != null) {
                return Response.ok(department).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Department not found\"}")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Internal server error: " + e.getMessage() + "\"}")
                    .build();
        }
    }
}
