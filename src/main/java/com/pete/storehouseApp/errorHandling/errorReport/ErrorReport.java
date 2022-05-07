package com.pete.storehouseApp.errorHandling.errorReport;

import org.springframework.stereotype.Component;

@Component
public class ErrorReport {

    String errorReport;

    public ErrorReport(){
        errorReport = "";
    }

    public void addError(String error){

        errorReport += (error);
    }

    public boolean hasErrors(){
        return !errorReport.isEmpty();
    }

    public void clear(){
        errorReport = "";
    }

    public String getErrorReport() {
        return errorReport;
    }

    public void setErrorReport(String errorReport) {
        this.errorReport = errorReport;
    }
}
