package com.pete.storehouseApp.validators;

import com.pete.storehouseApp.dto.ExitReceiptDTO;
import com.pete.storehouseApp.errorHandling.errorReport.ErrorReport;
import com.pete.storehouseApp.repositories.ExitReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExitReceiptValidator {

    @Autowired
    ErrorReport errorReport;

    @Autowired
    ExitReceiptRepository exitReceiptRepository;

    public void validateCreate(ExitReceiptDTO exitReceiptDTO){
        errorReport.clear();

        if (exitReceiptDTO.getDateOfExit() == null){
            errorReport.addError("Date cannot be empty. ");
        }

        if (exitReceiptDTO.getReasonForExit() == null || exitReceiptDTO.getReasonForExit().isEmpty()){
            errorReport.addError("Reason for exit cannot be empty. ");
        }

        if (exitReceiptDTO.getSender() == null || exitReceiptDTO.getSender().isEmpty()){
            errorReport.addError("Sender cannot be empty. ");
        }

        if (errorReport.hasErrors()){
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }

    public void validateUpdate(ExitReceiptDTO exitReceiptDTO) {
        errorReport.clear();

        if (!exitReceiptRepository.existsById(exitReceiptDTO.getId())){
            errorReport.addError("Exit receipt with given id does not exist. ");
        }

        if (errorReport.hasErrors()) {
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }

    public void validateDelete(Long id) {
        errorReport.clear();

        if (!exitReceiptRepository.existsById(id)) {

            errorReport.addError("Exit receipt with given id does not exist. ");
        }

        if (errorReport.hasErrors()) {
            throw new RuntimeException(errorReport.getErrorReport());
        }
    }

}
