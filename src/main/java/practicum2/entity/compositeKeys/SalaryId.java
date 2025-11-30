package practicum2.entity.compositeKeys;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class SalaryId implements Serializable {

    @Column(name = "emp_no")
    private int empNo;
    @Column(name = "from_date")
    private LocalDate fromDate;

    // Constructors
    public SalaryId() {}
    public SalaryId(int empNo, LocalDate fromDate) {
        this.empNo = empNo;
        this.fromDate = fromDate;
    }

    // Required for composite key
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryId that = (SalaryId) o;
        return empNo == that.empNo && Objects.equals(fromDate, that.fromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, fromDate);
    }

    // Getters
    public int getEmpNo() { return empNo; }
    public LocalDate getFromDate() { return fromDate; }

    //setters
    public void setEmpNo(int empNo) { this.empNo = empNo; }
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }
}
