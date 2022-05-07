package com.pete.storehouseApp.validators;

import com.pete.storehouseApp.dto.EntryReceiptDTO;
import com.pete.storehouseApp.errorHandling.errorReport.ErrorReport;
import com.pete.storehouseApp.repositories.EntryReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryReceiptValidator {

    @Autowired
    ErrorReport errorReport;

    @Autowired
    EntryReceiptRepository entryReceiptRepository;

    public void validateCreate(EntryReceiptDTO entryReceiptDTO) {
        errorReport.clear();

        if (entryReceiptDTO.getDateOfEntry() == null) {
            errorReport.addError("Date cannot be empty. ");
        }

        if (entryReceiptDTO.getDescription() == null || entryReceiptDTO.getDescription().isEmpty()) {
            errorReport.addError("Description cannot be empty. ");
        }

        if (entryReceiptDTO.getRecipient() == null || entryReceiptDTO.getRecipient().isEmpty()) {
            errorReport.addError("Recipient cannot be empty. ");
        }

        if (errorReport.hasErrors()) {
            throw new RuntimeException(errorReport.getErrorReport());
        }

    }

    public void validateUpdate(EntryReceiptDTO entryReceiptDTO) {
        errorReport.clear();

        if (!entryReceiptRepository.existsById(entryReceiptDTO.getId())){
            errorReport.addError("Entry receipt with given id does not exist. ");
        }

        if (errorReport.hasErrors()) {
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }


    public void validateDelete(Long id) {

        errorReport.clear();

        if (!entryReceiptRepository.existsById(id)) {

            errorReport.addError("Entry receipt with given id does not exist. ");
        }

        if (errorReport.hasErrors()) {
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }
}
