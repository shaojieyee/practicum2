package practicum2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import practicum2.entity.compositeKeys.DeptEmpId;

import java.time.LocalDate;

@Entity
@Table(name="dept_emp", schema = "employees")
public class DeptEmp {

    @EmbeddedId //composite key
    private DeptEmpId deptEmpId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="from_date")
    private LocalDate fromDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="to_date")
    private LocalDate toDate;

    //relationship
    @ManyToOne
    @MapsId("deptNo")
    @JoinColumn(name="dept_no", referencedColumnName = "dept_no")
    private Department department;
    @ManyToOne
    @MapsId("empNo")
    @JoinColumn(name="emp_no", referencedColumnName = "emp_no")
    private Employee employee;

    //constructors
    public DeptEmp(){}
    public DeptEmp(Employee empNo, Department deptNo, LocalDate from_date, LocalDate to_date){
        this.deptEmpId = new DeptEmpId(deptNo.getDeptNo(), empNo.getEmpNo());
        this.fromDate = from_date;
        this.toDate = to_date;
    }
    //getter
    public DeptEmpId DeptEmpId() {return this.deptEmpId;}
    public LocalDate getFromDate() {return this.fromDate;}
    public LocalDate getToDate() {return this.toDate;}
    //setter
    public void setDeptEmpId(Employee empNo, Department deptNo) {this.deptEmpId = new DeptEmpId(deptNo.getDeptNo(), empNo.getEmpNo());}
    public void setFromDate(LocalDate fromDate) {this.fromDate = fromDate;}
    public void setToDate(LocalDate toDate) {this.toDate = toDate;}
    //toString
    @Override
    public String toString(){
        return String.format("deptEmpId: %s, from_date: %s, to_date:%s",
                this.deptEmpId, this.fromDate, this.toDate);
    }
}
