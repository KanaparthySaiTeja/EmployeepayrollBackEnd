package com.cg.exceptions;

public class EmployeePayrollException extends Exception{

    public ExceptionTypes exceptionTypes;

    public EmployeePayrollException(ExceptionTypes exceptionTypes){
        this.exceptionTypes = exceptionTypes;
    }

    public enum ExceptionTypes{
        EMPLOYEE_NOT_FOUND("Invalid Data.. Employee not found"),
        OTHER_EXCEPTION("other exceptions");
        public String errorMessage;

        ExceptionTypes(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
