package practicum2.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "salaries", schema = "employees")
public class Salary {

    @Id
    @Column (name = "emp_no", precision = 11)
    private int empNo;
    @Column (name = "salary", precision = 11)
    private int salary;
    @Column (name = "from_date")
    private LocalDate fromDate;
    @Column (name = "to_date")
    private LocalDate toDate;

    //relationship
    @ManyToOne
    @JoinColumn(name="emp_no", referencedColumnName = "emp_no")
    private Employee employee;

    //constructor
    public Salary(){};
    public Salary(int emp_no, int salary, LocalDate from_date, LocalDate to_date) {
        this.empNo = emp_no;
        this.salary = salary;
        this.fromDate = from_date;
        this.toDate = to_date;
    }
    // getters
    public int getEmpNo() {return empNo;}
    public int getSalary() {return salary;}
    public LocalDate getFromDate() {return fromDate;}
    public LocalDate getToDate() {return toDate;}
    // setters
    public void setEmpNo(int emp_no) {this.empNo = emp_no;}
    public void setSalary(int salary) {this.salary = salary;}
    public void setFromDate(LocalDate from_date) {this.fromDate = from_date;}
    public void setToDate(LocalDate to_date) {this.toDate = to_date;}
    //toString
    public String toString(){
        return String.format("empNo: %s, salary: %s, firstName: %s, fromDate: %s, toDate: %s",
                this.empNo, this.salary, this.fromDate, this.toDate);
    }
}