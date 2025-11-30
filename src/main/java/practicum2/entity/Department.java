package practicum2.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "Department.findDistinctDepartments",
        query = "SELECT DISTINCT d FROM Department d"
)
@Entity
@Table(name = "departments", schema = "employees")
public class Department {

    @Id
    @Column(name="dept_no", length = 4) //limit to 4 char
    private String deptNo;
    @Column(name="dept_name", length = 40) //limit to 40 varchar
    private String deptName;

    //relationships
    @OneToMany(mappedBy = "department")
    private List<DeptEmp> DeptEmps =  new ArrayList<>();
    @OneToMany(mappedBy = "department")
    private List<DeptManager> DeptManagers =  new ArrayList<>();

    //constructors
    public Department(){}
    public Department(String dept_no, String dept_name){
        this.deptNo = dept_no;
        this.deptName = dept_name;
    }

    //getter
    public String getDeptNo() {return this.deptNo;}
    public String getDeptName() {return this.deptName;}

    //setter
    public void setDeptNo(String deptNo) {this.deptNo = deptNo;}
    public void setDeptName(String deptName) {this.deptName = deptName;}

    //toString
    @Override
    public String toString(){
        return String.format("deptNo: %s, deptName: %s",
                this.deptNo, this.deptName);
    }

}