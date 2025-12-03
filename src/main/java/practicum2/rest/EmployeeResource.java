package practicum2.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import practicum2.dto.EmployeeDto;
import practicum2.entity.Employee;
import practicum2.service.EmployeeDao;

import java.time.LocalDate;
import java.util.List;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    private EmployeeDao employeeDao;

    public EmployeeResource() {
        this.employeeDao = new EmployeeDao();
    }

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

    @POST
    @Path("/promote")// /promote?empNo=110022&newTitle=head%20chef&newDeptNo=d002&newManager=true&newSalary=123456&newStartEndDateStr=2025-12-12
    public Response promoteEmployee(
            @QueryParam("empNo") int empNo,
            @QueryParam("newTitle") String newTitle,
            @QueryParam("newDeptNo") String newDeptNo,
            @QueryParam("newManager") @DefaultValue("false") boolean newManager,
            @QueryParam("newSalary") int newSalary,
            @QueryParam("effectiveDate") String newStartEndDateStr
    ) {
        try {
            //check if empNo is provided
            if (empNo <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Employee number (empNo) is required and must be positive\"}")
                        .build();
            }
            //check if at least 1 new data is provided
            if (newTitle == null && newDeptNo == null && !newManager && newSalary <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"At least one promotion field must be provided (newTitle, newDeptNo, newManager, or newSalary)\"}")
                        .build();
            }
            //check isManger corresponds to title, vice versa
            if (newManager){
                newTitle = newTitle + "Manager";
            }
            if (newTitle != null && newTitle.toLowerCase().contains("manager")) {
                newManager = true;
            }

            //check if newStartEndDate is provided
            LocalDate newStartEndDate;
            if (newStartEndDateStr != null && !newStartEndDateStr.trim().isEmpty()) {
                try {
                    newStartEndDate = LocalDate.parse(newStartEndDateStr);
                }catch(Exception e){
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("{\"error\": \"Invalid date format. Use YYYY-MM-DD\"}")
                            .build();
                }
            } else {
                newStartEndDate = LocalDate.now();
            }

            //the actual processing
            //check if employee exists first
            Employee existingEmployee = employeeDao.findEmployeeByEmpNo(empNo);
            if (existingEmployee == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Employee not found\"}")
                        .build();
            }
            //then process promotion
            employeeDao.promote(empNo, newTitle, newDeptNo, newManager, newSalary, newStartEndDate);
            //print the updated employee
            Employee updatedEmployee = employeeDao.findEmployeeByEmpNo(empNo);
            return Response.ok(updatedEmployee).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Internal server error: " + e.getMessage() + "\"}")
                    .build();
        }
    }
}
