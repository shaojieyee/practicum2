package practicum2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "Employee.findByEmpNo",
        query = "SELECT DISTINCT e FROM Employee e WHERE e.empNo = :empNo")
@Entity
@Table(name = "employees", schema = "employees")
public class Employee {
    public enum Gender {M, F}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_no", precision = 11)
    private int empNo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "first_name", length = 14)
    private String firstName;
    @Column(name = "last_name" , length = 16)
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 1)
    private Gender gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "hire_date")
    private LocalDate hireDate;

    //relationship
    @OneToMany(mappedBy = "employee")
    @JsonIgnoreProperties("employee")
    private List<DeptEmp> DeptEmps = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    @JsonIgnoreProperties("employee")
    private List<DeptManager> DeptManagers = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    @JsonIgnoreProperties("employee")
    private List<Salary> salaries = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    @JsonIgnoreProperties("employee")
    private List<Title> titles = new ArrayList<>();

    //constructor
    public Employee() {}
    public Employee(int emp_no, LocalDate birth_date, String first_name, String last_name, Gender gender, LocalDate hire_date) {
        this.empNo = emp_no;
        this.birthDate = birth_date;
        this.firstName = first_name;
        this.lastName = last_name;
        this.hireDate = hire_date;
    }
    //getter
    public int getEmpNo() {return empNo;}
    public LocalDate getBirthDate() {return birthDate;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public Gender getGender() {return gender;}
    public LocalDate getHireDate() {return hireDate;}
    public List<Salary> getSalaries() { return salaries; }
    public List<Title> getTitles() {return titles;}

    //setter
    public void setEmpNo(int emp_no) {this.empNo = emp_no;}
    public void setBirthDate(LocalDate birth_date) {this.birthDate = birth_date;}
    public void setFirstName(String first_name) {this.firstName = first_name;}
    public void setLastName(String last_name) {this.lastName = last_name;}
    public void setGender(Gender gender) {this.gender = gender;}
    public void setHireDate(LocalDate hire_date) {this.hireDate = hire_date;}
    public void setSalaries(List<Salary> salaries) {this.salaries = salaries;}
    public void setTitles(List<Title> titles) {this.titles = titles;}

    //toString
    @Override
    public String toString() {
        return String.format("empNo: %s, birthDate: %s, firstName: %s, lastName: %s, gender: %s, hireDate: %s",
        this.empNo, this.birthDate, this.firstName, this.lastName, this.gender, this.hireDate);
    }
}