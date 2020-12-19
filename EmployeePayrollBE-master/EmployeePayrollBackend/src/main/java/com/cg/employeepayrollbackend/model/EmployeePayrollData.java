package com.cg.employeepayrollbackend.model;

import java.time.LocalDate;
import java.util.List;

import com.cg.employeepayrollbackend.dto.EmployeePayrollDTO;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "employee_payroll")
public @Data class EmployeePayrollData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "name")
    private String name;
    private long salary;
    private String gender;
    private String startDate;
    private String note;
    private String profilePic;

    @ElementCollection
    @CollectionTable(name = "employee_department",joinColumns = @JoinColumn(name = "id"))
    @Column(name = "department")
    private List<String> departments;

    public EmployeePayrollData() { }

    public EmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
        this.updateEmployeePayrollData(empPayrollDTO);
    }
    public void updateEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
        this.name = empPayrollDTO.name;
        this.salary = empPayrollDTO.salary;
        this.gender = empPayrollDTO.gender;
        this.note = empPayrollDTO.note;
        this.startDate = empPayrollDTO.startDate;
        this.profilePic = empPayrollDTO.profilePic;
        this.departments = empPayrollDTO.departments;
    }
}

