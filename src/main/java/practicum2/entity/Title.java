package practicum2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import practicum2.entity.compositeKeys.TitleId;

import java.time.LocalDate;

@Entity
@Table(name = "titles", schema = "employees")
public class Title {

    //relationship
    @EmbeddedId
    private TitleId titleId;

    @ManyToOne
    @MapsId("empNo")
    @JoinColumn(name="emp_no", referencedColumnName = "emp_no")
    @JsonIgnoreProperties("titles")
    private Employee employee;

    @Column(name="title", length = 50)
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="to_date")
    private LocalDate toDate;

    //constructor
    public Title(){}
    public Title(int emp_no, String title, LocalDate from_date, LocalDate to_date) {
        this.titleId = new TitleId(emp_no, from_date);
        this.title = title;
        this.toDate = to_date;
    }
    //getter
    public String getTitle() {return this.title;}
    @JsonGetter("fromDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate getFromDate() {return titleId !=null ? titleId.getFromDate(): null;}
    public LocalDate getToDate() {return this.toDate;}
    public Employee getEmployee() {return this.employee;}
    //setter
    public void setTitle(String title) {this.title = title;}
    public void setFromDate(LocalDate from_date) {this.titleId.setFromDate(from_date);}
    public void setToDate(LocalDate to_date) {this.toDate = to_date;}
    public void setEmployee(Employee employee) {this.employee = employee;}
    //toString
    public String toString(){
        return String.format("empNo: %s, title: %s, fromDate: %s, toDate: %s",
                this.employee != null ? this.employee.getEmpNo() : "null", this.title, this.titleId.getFromDate(), this.toDate);
    }
}