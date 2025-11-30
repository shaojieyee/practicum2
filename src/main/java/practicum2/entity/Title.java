package practicum2.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "titles", schema = "employees")
public class Title {

    @Id
    @Column(name="emp_no", precision = 11)
    private int empNo;
    @Column(name="title", length = 50)
    private String title;
    @Column(name="from_date")
    private LocalDate fromDate;
    @Column(name="to_date")
    private LocalDate toDate;

    //relationship
    @ManyToOne
    @JoinColumn(name="emp_no", referencedColumnName = "emp_no")
    private Employee employee;

    //constructor
    public Title(){}
    public Title(int emp_no, String title, LocalDate from_date, LocalDate to_date) {
        this.empNo = emp_no;
        this.title = title;
        this.fromDate = from_date;
        this.toDate = to_date;
    }
    //getter
    public int getEmp_no() {return this.empNo;}
    public String getTitle() {return this.title;}
    public LocalDate getFrom_date() {return this.fromDate;}
    public LocalDate getTo_date() {return this.toDate;}
    //setter
    public void setEmp_no(int emp_no) {this.empNo = emp_no;}
    public void setTitle(String title) {this.title = title;}
    public void setFrom_date(LocalDate from_date) {this.fromDate = from_date;}
    public void setTo_date(LocalDate to_date) {this.toDate = to_date;}
    //toString
    public String toString(){
        return String.format("empNo: %s, title: %s, fromDate: %s, toDate: %s",
                this.empNo, this.title, this.fromDate, this.toDate);
    }
}