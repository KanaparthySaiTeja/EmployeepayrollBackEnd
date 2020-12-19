package com.cg.dto;


import com.cg.domain.EmployeePayroll;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class EmployeePayrollDto {

    private Long id;
    @NotNull(message = "Please enter valid name")
    @NotEmpty(message = "Please enter valid name")
    @Pattern(regexp = "^[A-Z][a-z]{3,}",message="Enter the valid name")
    private String name;

    @Min(value = 500,message="Min wage is more than 500")
    private String salary;

    public EmployeePayrollDto(){

    }

    public EmployeePayrollDto(EmployeePayroll user){
        this.id = user.getId();
        this.name = user.getName();
        this.salary = user.getSalary();
    }

    @Override
    public String toString() {
        return "EmployeePayrollDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
