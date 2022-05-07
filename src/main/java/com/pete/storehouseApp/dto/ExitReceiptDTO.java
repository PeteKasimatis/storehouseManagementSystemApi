package com.pete.storehouseApp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class ExitReceiptDTO {

    private Long id;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateOfExit;

    private String reasonForExit;

    private String sender;

    private List<ExitRegistrationDTO> exitRegistrationDTOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfExit() {
        return dateOfExit;
    }

    public void setDateOfExit(Date dateOfExit) {
        this.dateOfExit = dateOfExit;
    }

    public String getReasonForExit() {
        return reasonForExit;
    }

    public void setReasonForExit(String reasonForExit) {
        this.reasonForExit = reasonForExit;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<ExitRegistrationDTO> getExitRegistrationDTOList() {
        return exitRegistrationDTOList;
    }

    public void setExitRegistrationDTOList(List<ExitRegistrationDTO> exitRegistrationDTOList) {
        this.exitRegistrationDTOList = exitRegistrationDTOList;
    }
}
