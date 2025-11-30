package practicum2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import practicum2.entity.compositeKeys.SalaryId;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "salaries", schema = "employees")
public class Salary {

    @EmbeddedId
    private SalaryId salaryId;

    //relationship
    @ManyToOne
    @MapsId("empNo")
    @JoinColumn(name="emp_no", referencedColumnName = "emp_no")
    @JsonIgnoreProperties("salaries")
    private Employee employee;

    @Column (name = "salary", precision = 11)
    private int salary;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column (name = "to_date")
    private LocalDate toDate;

    //constructor
    public Salary(){};
    public Salary(int emp_no, int salary, LocalDate from_date, LocalDate to_date) {
        this.salaryId = new  SalaryId(emp_no, from_date);
        this.salary = salary;
        this.toDate = to_date;
    }
    // getters
    public Employee getEmployee() {return this.employee;}
    public int getSalary() {return salary;}
    @JsonGetter("fromDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate getFromDate() {return salaryId !=null ? salaryId.getFromDate(): null;}
    public LocalDate getToDate() {return toDate;}
    // setters
    public void setEmployee(Employee employee) {this.employee = employee;}
    public void setSalary(int salary) {this.salary = salary;}
    public void setFromDate(LocalDate from_date) {salaryId.setFromDate(from_date);}
    public void setToDate(LocalDate to_date) {this.toDate = to_date;}
    //toString
    public String toString(){
        return String.format("empNo: %s, salary: %s, firstName: %s, fromDate: %s, toDate: %s",
                this.employee != null ? this.employee.getEmpNo() : "null", this.salary, getFromDate(), this.toDate);
    }
}