package com.cg.employeepayrollbackend.dto;

import java.util.List;

import java.time.LocalDate;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;

public @ToString
class EmployeePayrollDTO {

    @NotEmpty(message = "Employee name must not be null")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee name Invalid")
    public String name;

    @Min(value = 500, message = "Min Wage should be more than 500")
    public long salary;

    @Pattern(regexp = "male|female",message = "Gender needs to be male or female")
    public String gender;

   // @JsonFormat(pattern = "dd MMM yyyy")
    @NotNull(message = "startDate should Not be Empty")
   // @PastOrPresent(message = "startDate should not be a future date")
    public String startDate;

    @NotBlank(message = "Note cannot be empty")
    public String note;

    @NotBlank(message = "profilePic cannot be Empty")
    public String profilePic;

    @NotNull(message = "department should Not be Empty")
    public List<String> departments;

}
