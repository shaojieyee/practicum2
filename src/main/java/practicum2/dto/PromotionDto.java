package practicum2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class PromotionDto {

    //get and add new title if any, new department if any, new salary if any
    @NotNull
    private int empNo;
    private String newTitle; //if any
    private String newDeptNo; //if any
    private int newSalary; //if any
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate newStartEndDate;

    //if any field above gets updated, get the previous relevant fields and update the to_Date
    private String prevTitle;
    private String prevDeptNo;
    private String prevSalary;

    //constructors
    PromotionDto() {}
    PromotionDto(int empNo) {
        this.empNo = empNo;
        this.newStartEndDate = LocalDate.now();
    }

    //getters
    public int getEmpNo() {return this.empNo;}
    public String getNewTitle() {return this.newTitle;}
    public String getNewDeptNo() {return this.newDeptNo;}
    public int getNewSalary() {return this.newSalary;}
    public LocalDate getNewStartEndDate() {return this.newStartEndDate;}
    public String getPrevTitle() {return this.prevTitle;}
    public String getPrevDeptNo() {return this.prevDeptNo;}
    public String getPrevSalary() {return this.prevSalary;}

    //setters
    public void setEmpNo(int empNo) {this.empNo = empNo;}
    public void setNewTitle(String newTitle) {this.newTitle = newTitle;}
    public void setNewDeptNo(String newDeptNo) {this.newDeptNo = newDeptNo;}
    public void setNewSalary(int newSalary) {this.newSalary = newSalary;}
    public void setPrevTitle(String prevTitle) {this.prevTitle = prevTitle;}
    public void setPrevDeptNo(String prevDeptNo) {this.prevDeptNo = prevDeptNo;}
    public void setPrevSalary(String prevSalary) {this.prevSalary = prevSalary;}
}
