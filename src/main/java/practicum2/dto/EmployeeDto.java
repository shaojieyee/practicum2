package practicum2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import practicum2.entity.Employee;

import java.time.LocalDate;

public class EmployeeDto {

    private int empNo;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;

    //constructor
    public EmployeeDto(){}
    public EmployeeDto(Employee employee) {
        this.empNo = employee.getEmpNo();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.hireDate = employee.getHireDate();
    }
    public EmployeeDto(int empNo, String firstName, String lastName, LocalDate hireDate) {
        this.empNo = empNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
    }
    //getter
    public int getEmpNo() {return this.empNo;}
    public String getFirstName() {return this.firstName;}
    public String getLastName() {return this.lastName;}
    public LocalDate getHireDate() {return this.hireDate;}
    //setter
    public void setEmpNo(int empNo) {this.empNo = empNo;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setHireDate(LocalDate hireDate) {this.hireDate = hireDate;}
}
