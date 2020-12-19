package com.bl.dto;


import com.bl.domain.EmployeePayroll;
import lombok.Data;

@Data
public class EmployeePayrollDto {



    private Long id;
    private String name;
    private String salary;

    public EmployeePayrollDto(){

    }

    public EmployeePayrollDto(EmployeePayroll user){
        this.id = user.getId();
        this.name = user.getName();
        this.salary = user.getSalary();
    }
}
