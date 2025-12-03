package practicum2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import practicum2.entity.compositeKeys.DeptManagerId;

import java.time.LocalDate;

@Entity
@Table(name="dept_manager", schema = "employees")
public class DeptManager {

    @EmbeddedId
    private DeptManagerId deptManagerId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="from_date")
    private LocalDate fromDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="to_date")
    private LocalDate toDate;

    //relationship
    @ManyToOne
    @MapsId("deptNo")
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no")
    @JsonIgnoreProperties("deptManagers")
    private Department department;
    @ManyToOne
    @MapsId("empNo")
    @JoinColumn(name="emp_no", referencedColumnName = "emp_no")
    @JsonIgnoreProperties("deptManagers")
    private Employee employee;

    //constructors
    public DeptManager(){}
    public DeptManager(String dept_no, int emp_no, LocalDate from_date, LocalDate to_date){
        this.deptManagerId = new DeptManagerId(dept_no, emp_no);
        this.fromDate = from_date;
        this.toDate = to_date;
    }
    //getter
    @JsonIgnore
    public DeptManagerId getDeptManagerId() {return this.deptManagerId;}
    public LocalDate getFromDate() {return this.fromDate;}
    public LocalDate getToDate() {return this.toDate;}
    public String getManagerName(){return this.employee.getFirstName() + " " + this.employee.getLastName();}
    @JsonIgnore
    public Employee getEmployee() {return this.employee;}
    public Department getDepartment() {return this.department;}
    //setter
    public void setDeptManagerId(String dept_no, int emp_no) {this.deptManagerId = new DeptManagerId(dept_no, emp_no);}
    public void setFromDate(LocalDate from_date) {this.fromDate = from_date;}
    public void setToDate(LocalDate to_date) {this.toDate = to_date;}
    public void setManagerName(String firstName, String lastName){this.employee.setFirstName(firstName);this.employee.setLastName(lastName);}
    public void setEmployee(Employee employee) {this.employee = employee;}
    public void setDepartment(Department department) {this.department = department;}

}
