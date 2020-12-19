package com.bl.service;

import com.bl.domain.EmployeePayroll;
import com.bl.dto.EmployeePayrollDto;
import com.bl.exceptions.DetailsNotProvidedExceptions;
import com.bl.exceptions.UserNotFound;
import com.bl.repository.EmployeePayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmployeePayrollService {

    @Autowired
    private EmployeePayrollRepository employeePayrollRepository;

    public EmployeePayrollDto CreateUser(EmployeePayrollDto employeePayrollDto){
        if(Objects.nonNull(employeePayrollDto.getName()) && Objects.nonNull(employeePayrollDto.getSalary())) {
            EmployeePayroll employeePayroll = new EmployeePayroll(employeePayrollDto.getName(),employeePayrollDto.getSalary());
            return new EmployeePayrollDto(employeePayrollRepository.save(employeePayroll));
        }

        throw new DetailsNotProvidedExceptions("Invalid Data");
    }

    public EmployeePayrollDto UpdateUser(EmployeePayrollDto employeePayrollDto){

        return employeePayrollRepository.findById(employeePayrollDto.getId()).map(employeePayroll -> {
            if(Objects.nonNull(employeePayrollDto.getName())){
                employeePayroll.setName(employeePayrollDto.getName());
            }
            if(Objects.nonNull(employeePayroll.getSalary())){
                employeePayroll.setSalary(employeePayroll.getSalary());
            }
           return new EmployeePayrollDto(employeePayrollRepository.save(employeePayroll));
        }).orElseThrow(()-> new UserNotFound("UserNotFound"));
    }

    public EmployeePayrollDto deleteUser(Long id){
        return employeePayrollRepository.findById(id).map(employeePayroll -> {
            employeePayrollRepository.deleteById(employeePayroll.getId());
            return new EmployeePayrollDto(employeePayroll);
        }).orElseThrow(()-> new UserNotFound("UserNotFound"));
    }


    public List<EmployeePayrollDto> getAllUser(){
        return employeePayrollRepository.findAll()
                .stream()
                .map(employeePayroll -> new EmployeePayrollDto(employeePayroll))
                .collect(Collectors.toList());
    }
}
//curl -X POST -H "Content-Type: application/json" -d '{"name":"pooja","salary":"456611"}' "http://localhost:8080/employee-payroll/create"
/*curl -X PUT -H "Content-Type: application/json" -d '{"id":2,"name":"Aditya","salary":"456611"}' "http://localhost:8080/employee-payroll/update"
{"id":2,"name":"Aditya","salary":"9568"}

 curl -X GET "http://localhost:8080/employee-payroll/get"

curl -X DELETE "http://localhost:8080/employee-payroll/delete/3"

curl -X POST -H "Content-Type: application/json" -d "{\"name\":\"prajwal\",\"salary\":\"123456\"}" "http://localhost:8080/employeepayroll/create"

ng g c component_name
ng generate component name

@CrossOrigin(allowedHeaders = "*", origins = "*")

npm i @angular/http
*/
