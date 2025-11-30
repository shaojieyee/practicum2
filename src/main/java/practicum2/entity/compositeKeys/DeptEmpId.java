package practicum2.entity.compositeKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DeptEmpId implements Serializable {

    //take in the dept_no from departments
    @Column(name = "dept_no", length = 4)
    private String deptNo;
    //take in the emp_no from employees
    @Column(name = "emp_No", precision = 11)
    private int empNo;

    //constructors
    public DeptEmpId() {}
    public DeptEmpId(String deptNo, int empNo) {
        this.deptNo = deptNo;
        this.empNo = empNo;
    }

    //required for composite key
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptEmpId that = (DeptEmpId) o;
        return Objects.equals(deptNo, that.deptNo) &&
                Objects.equals(empNo, that.empNo);
    }
    @Override
    public int hashCode() {
        return Objects.hash(deptNo, empNo);
    }

    //getters
    public String getDeptNo() { return this.deptNo; }
    public int getEmpNo() { return this.empNo; }
    //setters
    public void setDeptNo(String deptNo) { this.deptNo = deptNo; }
    public void setEmpNo(int empNo) { this.empNo = empNo; }

    //toString
    @Override
    public String toString() {
        return "DeptEmpId: deptNo=" + deptNo + ", empNo='" + empNo;
    }
}
