package practicum2.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import practicum2.dto.EmployeeDto;
import practicum2.dto.PromotionDto;
import practicum2.entity.Employee;
import practicum2.service.EmployeeDao;

import java.util.List;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    private EmployeeDao employeeDao;
    public EmployeeResource() {this.employeeDao = new EmployeeDao();}

    @GET
    @Path("/ping") // /ping
    public Response ping() {
        return Response.ok().entity("Employee service online").build();
    }

    @GET
    @Path("/id") // /id?empNo=110022
    public Response getEmployeeByEmpNo(@QueryParam("empNo") int empNo) {
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

    @GET
    @Path("/department") // /department?deptNo=d001&page=1
    public Response getEmployeeByDeptNo(@QueryParam("deptNo") String deptNo, @QueryParam("page") int page) {
        try {
            List<EmployeeDto> employees = employeeDao.findEmployeesByDept(deptNo, page);
            if (employees != null) {
                return Response.ok(employees).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Employees not found\"}")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Internal server error: " + e.getMessage() + "\"}")
                    .build();
        }
    }

//    @POST
//    @Path("/promote") // /promotion?deptNo=d001&page=1
//    public Response promoteEmployee(PromotionDto promotion){
//        try {
//
//
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                    .entity("{\"error\": \"Internal server error: " + e.getMessage() + "\"}")
//                    .build();
//        }
//    }
}
