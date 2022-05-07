package com.pete.storehouseApp.services.impl;

import com.pete.storehouseApp.dto.ExitReceiptDTO;
import com.pete.storehouseApp.dto.ExitRegistrationDTO;
import com.pete.storehouseApp.models.ExitReceipt;
import com.pete.storehouseApp.models.ExitRegistration;
import com.pete.storehouseApp.repositories.ExitReceiptRepository;
import com.pete.storehouseApp.services.ExitReceiptService;
import com.pete.storehouseApp.services.ExitRegistrationService;
import com.pete.storehouseApp.services.ProductShelfService;
import com.pete.storehouseApp.validators.ExitReceiptValidator;
import com.pete.storehouseApp.validators.ExitRegistrationValidator;
import com.pete.storehouseApp.validators.ProductShelfValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExitReceiptServiceImpl implements ExitReceiptService {

    @Autowired
    ExitReceiptValidator exitReceiptValidator;

    @Autowired
    ExitReceiptRepository exitReceiptRepository;

    @Autowired
    ExitRegistrationService exitRegistrationService;

    @Autowired
    ExitRegistrationValidator exitRegistrationValidator;

    @Autowired
    ProductShelfService productShelfService;

    @Autowired
    ProductShelfValidator productShelfValidator;


    private ExitReceipt dtoToEntity(ExitReceiptDTO exitReceiptDTO){

        ExitReceipt exitReceipt = new ExitReceipt();

        BeanUtils.copyProperties(exitReceiptDTO, exitReceipt);

        exitReceipt.setExitRegistrations(fillExitRegistrationList(exitReceiptDTO, exitReceipt));

        return exitReceipt;
    }

    private ExitReceiptDTO entityToDTO(ExitReceipt exitReceipt){

        ExitReceiptDTO exitReceiptDTO = new ExitReceiptDTO();

        BeanUtils.copyProperties(exitReceipt, exitReceiptDTO);

        return exitReceiptDTO;
    }


    private List<ExitRegistration> fillExitRegistrationList(ExitReceiptDTO exitReceiptDTO, ExitReceipt exitReceipt){

        List<ExitRegistration> exitRegistrationList = new ArrayList<>();

        for (ExitRegistrationDTO exitRegistrationDTO : exitReceiptDTO.getExitRegistrationDTOList()){
            //check if given shelf contains given product
            productShelfValidator.validate(exitRegistrationDTO);

            exitRegistrationValidator.validate(exitRegistrationDTO);
            ExitRegistration exitRegistration = exitRegistrationService.dtoToEntity(exitRegistrationDTO);
            exitRegistration.setExitReceipt(exitReceipt);
            exitRegistrationList.add(exitRegistration);
        }

        return exitRegistrationList;
    }


    @Override
    public ExitReceiptDTO create(ExitReceiptDTO exitReceiptDTO) {

        exitReceiptValidator.validateCreate(exitReceiptDTO);

        ExitReceipt exitReceipt = dtoToEntity(exitReceiptDTO);

        /*
        For each registration update the appropriate entry in ProductShelf
         */
        for (ExitRegistration exitRegistration: exitReceipt.getExitRegistrations()){
            productShelfService.updateProductInShelf(exitRegistration, "subtract");
        }

        exitReceiptRepository.save(exitReceipt);

        return entityToDTO(exitReceipt);
    }

    @Override
    public ExitReceiptDTO update(ExitReceiptDTO exitReceiptDTO) {

        exitReceiptValidator.validateUpdate(exitReceiptDTO);

        ExitReceipt exitReceipt = exitReceiptRepository.getById(exitReceiptDTO.getId());

        if (exitReceiptDTO.getDateOfExit() != null){
            exitReceipt.setDateOfExit(exitReceiptDTO.getDateOfExit());
        }

        if (exitReceiptDTO.getReasonForExit() != null){
            exitReceipt.setReasonForExit(exitReceiptDTO.getReasonForExit());
        }

        if (exitReceiptDTO.getSender() != null){
            exitReceipt.setSender(exitReceiptDTO.getSender());
        }

        if (exitReceiptDTO.getExitRegistrationDTOList() != null){

            //update quantity in product-shelf association table for registrations
            //that are going to be deleted
            for (ExitRegistration exitRegistration : exitReceipt.getExitRegistrations()) {
                productShelfService.updateProductInShelf(exitRegistration, "add");
            }

            //delete registrations for given receipt
            exitRegistrationService.deleteByReceiptId(exitReceiptDTO.getId());

            exitReceipt.getExitRegistrations().clear();

            exitReceipt.setExitRegistrations(fillExitRegistrationList(exitReceiptDTO, exitReceipt));

            //For each registration update the appropriate entry in ProductShelf
            for (ExitRegistration exitRegistration: exitReceipt.getExitRegistrations()){
                productShelfService.updateProductInShelf(exitRegistration, "subtract");
            }
        }

        exitReceiptRepository.save(exitReceipt);

        return entityToDTO(exitReceipt);
    }

    @Override
    public void delete(Long id) {
        exitReceiptValidator.validateDelete(id);

        ExitReceipt exitReceipt = exitReceiptRepository.getById(id);

        for (ExitRegistration exitRegistration: exitReceipt.getExitRegistrations()){
            productShelfService.updateProductInShelf(exitRegistration, "add");
        }

        exitReceiptRepository.deleteById(id);
    }

    @Override
    public List<ExitReceiptDTO> findAll() {

        List<ExitReceipt> exitReceiptList = exitReceiptRepository.findAll();

        List<ExitReceiptDTO> exitReceiptDTOList = new ArrayList<>();

        for (ExitReceipt exitReceipt: exitReceiptList){
            exitReceiptDTOList.add(entityToDTO(exitReceipt));
        }

        return exitReceiptDTOList;
    }

    @Override
    public ExitReceiptDTO findById(Long id) {
        ExitReceiptDTO exitReceiptDTO = entityToDTO(exitReceiptRepository.getById(id));
        exitReceiptDTO.setExitRegistrationDTOList(exitRegistrationService.getByReceiptId(id));
        return exitReceiptDTO;
    }
}
