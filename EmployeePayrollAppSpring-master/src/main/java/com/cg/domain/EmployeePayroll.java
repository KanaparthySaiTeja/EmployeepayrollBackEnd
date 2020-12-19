package com.cg.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "employee")
public class EmployeePayroll implements Serializable {

    private static final long serialVersionUID = -8900492704842756948L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String salary;

    public EmployeePayroll() {

    }

    public EmployeePayroll(String name, String salary) {
        this.name = name;
        this.salary = salary;
    }

}