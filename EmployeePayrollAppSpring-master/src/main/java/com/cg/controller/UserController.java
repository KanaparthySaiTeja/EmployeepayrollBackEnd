package com.cg.controller;

import com.cg.domain.EmployeePayroll;
import com.cg.dto.EmployeePayrollDto;
import com.cg.dto.ResponseDto;
import com.cg.exceptions.EmployeePayrollException;
import com.cg.exceptions.UserNotFound;
import com.cg.service.EmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UserController {

    @Autowired
    EmployeePayrollService employeePayrollService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createUser(@RequestBody @Valid  EmployeePayrollDto user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<ResponseDto>(new ResponseDto((bindingResult.getAllErrors().get(0).getDefaultMessage()),"404"),HttpStatus.BAD_REQUEST);
        }
        try{
            EmployeePayrollDto employeePayrollDto = employeePayrollService.CreateUser(user);
            ResponseDto responseDto = new ResponseDto("User created succesfully",employeePayrollDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch ( EmployeePayrollException e){
            ResponseDto responseDto = new ResponseDto("User not created succesfully","404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateUser(@RequestBody @Valid EmployeePayrollDto user,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<ResponseDto>(new ResponseDto((bindingResult.getAllErrors().get(0).getDefaultMessage()),"404"),HttpStatus.BAD_REQUEST);
        }
        try{
            EmployeePayrollDto employeePayrollDto = employeePayrollService.UpdateUser(user);
            ResponseDto responseDto = new ResponseDto("User updated succesfully",employeePayrollDto);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch ( EmployeePayrollException e){
            ResponseDto responseDto = new ResponseDto("Update Call Unsuccesfull","404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }
    }

    //http://localhost:8080/employee-payroll/delete/1
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable("id") @Valid Long id,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<ResponseDto>(new ResponseDto((bindingResult.getAllErrors().get(0).getDefaultMessage()),"404"),HttpStatus.BAD_REQUEST);
        }
        try{
            EmployeePayrollDto employeePayrollDto = employeePayrollService.deleteUser(id);
            ResponseDto responseDto = new ResponseDto("User deleted succesfully",employeePayrollDto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
        } catch ( EmployeePayrollException e){
            ResponseDto responseDto = new ResponseDto("Delete Call UnSuccesfull","404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getAllUser(){
        try{
            List<EmployeePayrollDto> employeePayrollDto = employeePayrollService.getAllUser();
            ResponseDto responseDto = new ResponseDto("Get Call Succesfull",employeePayrollDto);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (Exception e){
            ResponseDto responseDto = new ResponseDto("Get Call Unsuccessfull","404");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }
}
