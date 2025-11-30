package practicum2.entity;

import jakarta.persistence.*;
import practicum2.entity.compsiteKeys.DeptManagerId;

import java.time.LocalDate;

@Entity
@Table(name="dept_manager", schema = "employees")
public class DeptManager {

    @EmbeddedId
    private DeptManagerId deptManagerId;
    @Column(name="from_date")
    private LocalDate fromDate;
    @Column(name="to_date")
    private LocalDate toDate;

    //relationship
    @ManyToOne
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no")
    private Department department;
    @ManyToOne
    @JoinColumn(name="emp_no", referencedColumnName = "emp_no")
    private Employee employee;

    //constructors
    public DeptManager(){}
    public DeptManager(String dept_no, int emp_no, LocalDate from_date, LocalDate to_date){
        this.deptManagerId = new DeptManagerId(dept_no, emp_no);
        this.fromDate = from_date;
        this.toDate = to_date;
    }
    //getter
    public DeptManagerId getDeptManagerId() {return this.deptManagerId;}
    public LocalDate getFromDate() {return this.fromDate;}
    public LocalDate getToDate() {return this.toDate;}
    //setter
    public void setDeptManagerId(String dept_no, int emp_no) {new DeptManagerId(dept_no, emp_no);}
    public void setFromDate(LocalDate from_date) {this.fromDate = from_date;}
    public void setToDate(LocalDate to_date) {this.toDate = to_date;}
    //toString
    @Override
    public String toString(){
        return String.format("deptManagerId: %s, from_date: %s, to_date:%s",
                this.deptManagerId, this.fromDate, this.toDate);
    }
}
