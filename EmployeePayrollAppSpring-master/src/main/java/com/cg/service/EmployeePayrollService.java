package com.cg.service;

import com.cg.domain.EmployeePayroll;
import com.cg.dto.EmployeePayrollDto;
import com.cg.exceptions.DetailsNotProvidedExceptions;
import com.cg.exceptions.EmployeePayrollException;
import com.cg.exceptions.UserNotFound;
import com.cg.repository.EmployeePayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmployeePayrollService {

    @Autowired
    private EmployeePayrollRepository employeePayrollRepository;

    public EmployeePayrollDto CreateUser(EmployeePayrollDto employeePayrollDto) throws EmployeePayrollException {
        if (Objects.nonNull(employeePayrollDto.getName()) && Objects.nonNull(employeePayrollDto.getSalary())) {
            EmployeePayroll employeePayroll = new EmployeePayroll(employeePayrollDto.getName(), employeePayrollDto.getSalary());
            return new EmployeePayrollDto(employeePayrollRepository.save(employeePayroll));
        }
        throw new EmployeePayrollException(EmployeePayrollException.ExceptionTypes.EMPLOYEE_NOT_FOUND);
    }

    public EmployeePayrollDto UpdateUser(EmployeePayrollDto employeePayrollDto) throws EmployeePayrollException {

        return employeePayrollRepository.findById(employeePayrollDto.getId()).map(employeePayroll -> {
            if(Objects.nonNull(employeePayrollDto.getName())){
                employeePayroll.setName(employeePayrollDto.getName());
            }
            if(Objects.nonNull(employeePayrollDto.getSalary())){
                employeePayroll.setSalary(employeePayrollDto.getSalary());
            }
           return new EmployeePayrollDto(employeePayrollRepository.save(employeePayroll));
        }).orElseThrow(()-> new EmployeePayrollException( EmployeePayrollException.ExceptionTypes.EMPLOYEE_NOT_FOUND));
    }

    public EmployeePayrollDto deleteUser(Long id) throws EmployeePayrollException {
        return employeePayrollRepository.findById(id).map(employeePayroll -> {
            employeePayrollRepository.deleteById(employeePayroll.getId());
            return new EmployeePayrollDto(employeePayroll);
        }).orElseThrow(()-> new EmployeePayrollException(EmployeePayrollException.ExceptionTypes.EMPLOYEE_NOT_FOUND));
    }

    public List<EmployeePayrollDto> getAllUser(){
        return employeePayrollRepository.findAll()
                .stream()
                .map(employeePayroll -> new EmployeePayrollDto(employeePayroll))
                .collect(Collectors.toList());
    }
}
