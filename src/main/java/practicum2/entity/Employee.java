package practicum2.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees", schema = "employees")
public class Employee {
    public enum Gender {M,F}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_no", precision = 11)
    private int empNo;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "first_name", length = 14)
    private String firstName;
    @Column(name = "last_name" , length = 16)
    private String lastName;
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "hire_date")
    private LocalDate hireDate;

    //relationship
    @OneToMany(mappedBy = "employee")
    private List<DeptEmp> DeptEmps;
    @OneToMany(mappedBy = "employee")
    private List<DeptManager> DeptManagers;
    @OneToMany(mappedBy = "employee")
    private List<Salary> salaries;
    @OneToMany(mappedBy = "employee")
    private List<Title> titles;

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
    //setter
    public void setEmpNo(int emp_no) {this.empNo = emp_no;}
    public void setBirthDate(LocalDate birth_date) {this.birthDate = birth_date;}
    public void setFirstName(String first_name) {this.firstName = first_name;}
    public void setLastName(String last_name) {this.lastName = last_name;}
    public void setGender(Gender gender) {this.gender = gender;}
    public void setHireDate(LocalDate hire_date) {this.hireDate = hire_date;}
    //toString
    @Override
    public String toString() {
        return String.format("empNo: %s, birthDate: %s, firstName: %s, lastName: %s, gender: %s, hireDate: %s",
        this.empNo, this.birthDate, this.firstName, this.lastName, this.gender, this.hireDate);
    }
}