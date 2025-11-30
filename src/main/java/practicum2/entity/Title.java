package practicum2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "titles", schema = "employees")
public class Title {

    //relationship
    @Id
    @ManyToOne
    @JoinColumn(name="emp_no", referencedColumnName = "emp_no")
    private Employee employee;

    @Column(name="title", length = 50)
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="from_date")
    private LocalDate fromDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="to_date")
    private LocalDate toDate;

    //constructor
    public Title(){}
    public Title(Employee employee, String title, LocalDate from_date, LocalDate to_date) {
        this.employee = employee;
        this.title = title;
        this.fromDate = from_date;
        this.toDate = to_date;
    }
    //getter
    public Employee getEmp_no() {return this.employee;}
    public String getTitle() {return this.title;}
    public LocalDate getFrom_date() {return this.fromDate;}
    public LocalDate getTo_date() {return this.toDate;}
    //setter
    public void setEmp_no(Employee employee) {this.employee = employee;}
    public void setTitle(String title) {this.title = title;}
    public void setFrom_date(LocalDate from_date) {this.fromDate = from_date;}
    public void setTo_date(LocalDate to_date) {this.toDate = to_date;}
    //toString
    public String toString(){
        return String.format("empNo: %s, title: %s, fromDate: %s, toDate: %s",
                this.employee != null ? this.employee.getEmpNo() : "null", this.title, this.fromDate, this.toDate);
    }
}