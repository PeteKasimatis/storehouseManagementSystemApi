package com.pete.storehouseApp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class EntryReceiptDTO {

    private Long id;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateOfEntry;

    private String description;

    private String recipient;

    private List<EntryRegistrationDTO> entryRegistrationDTOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Date dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public List<EntryRegistrationDTO> getEntryRegistrationDTOList() {
        return entryRegistrationDTOList;
    }

    public void setEntryRegistrationDTOList(List<EntryRegistrationDTO> entryRegistrationDTOList) {
        this.entryRegistrationDTOList = entryRegistrationDTOList;
    }
}
