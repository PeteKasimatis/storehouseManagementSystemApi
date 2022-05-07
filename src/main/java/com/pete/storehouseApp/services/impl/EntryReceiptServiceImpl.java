package com.pete.storehouseApp.services.impl;

import com.pete.storehouseApp.dto.EntryReceiptDTO;
import com.pete.storehouseApp.dto.EntryRegistrationDTO;
import com.pete.storehouseApp.models.EntryReceipt;
import com.pete.storehouseApp.models.EntryRegistration;
import com.pete.storehouseApp.repositories.EntryReceiptRepository;
import com.pete.storehouseApp.services.EntryReceiptService;
import com.pete.storehouseApp.services.EntryRegistrationService;
import com.pete.storehouseApp.services.ProductShelfService;
import com.pete.storehouseApp.validators.EntryReceiptValidator;
import com.pete.storehouseApp.validators.EntryRegistrationValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntryReceiptServiceImpl implements EntryReceiptService {

    @Autowired
    EntryReceiptValidator entryReceiptValidator;

    @Autowired
    EntryReceiptRepository entryReceiptRepository;

    @Autowired
    EntryRegistrationService entryRegistrationService;

    @Autowired
    EntryRegistrationValidator entryRegistrationValidator;

    @Autowired
    ProductShelfService productShelfService;

    private EntryReceipt dtoToEntity(EntryReceiptDTO entryReceiptDTO){

        EntryReceipt entryReceipt = new EntryReceipt();

        BeanUtils.copyProperties(entryReceiptDTO, entryReceipt);

        //fill the list of registrations for the receipt
        entryReceipt.setEntryRegistrations(fillEntryRegistrationList(entryReceiptDTO, entryReceipt));

        return entryReceipt;
    }

    private EntryReceiptDTO entityToDTO(EntryReceipt entryReceipt){

        EntryReceiptDTO entryReceiptDTO = new EntryReceiptDTO();

        BeanUtils.copyProperties(entryReceipt, entryReceiptDTO);

        return entryReceiptDTO;
    }


    private List<EntryRegistration> fillEntryRegistrationList(EntryReceiptDTO entryReceiptDTO, EntryReceipt entryReceipt){

        List<EntryRegistration> entryRegistrationList = new ArrayList<>();

        for (EntryRegistrationDTO entryRegistrationDTO : entryReceiptDTO.getEntryRegistrationDTOList()){

            entryRegistrationValidator.validateCreate(entryRegistrationDTO);

            EntryRegistration entryRegistration = entryRegistrationService.dtoToEntity(entryRegistrationDTO);
            entryRegistration.setEntryReceipt(entryReceipt);
            entryRegistrationList.add(entryRegistration);
        }

        return entryRegistrationList;
    }


    @Override
    public EntryReceiptDTO create(EntryReceiptDTO entryReceiptDTO) {

        entryReceiptValidator.validateCreate(entryReceiptDTO);

        EntryReceipt entryReceipt = dtoToEntity(entryReceiptDTO);

        /*
        For each registration update the appropriate entry in ProductShelf
         */
        for (EntryRegistration entryRegistration: entryReceipt.getEntryRegistrations()){
            productShelfService.updateProductInShelf(entryRegistration, "add");
        }

        entryReceiptRepository.save(entryReceipt);

        return entityToDTO(entryReceipt);
    }

    @Override
    public EntryReceiptDTO update(EntryReceiptDTO entryReceiptDTO) {

        entryReceiptValidator.validateUpdate(entryReceiptDTO);

        EntryReceipt entryReceipt = entryReceiptRepository.getById(entryReceiptDTO.getId());

        if (entryReceiptDTO.getDateOfEntry() != null){
            entryReceipt.setDateOfEntry(entryReceiptDTO.getDateOfEntry());
        }

        if (entryReceiptDTO.getDescription() != null){
            entryReceipt.setDescription(entryReceiptDTO.getDescription());
        }

        if (entryReceiptDTO.getRecipient() != null){
            entryReceipt.setRecipient(entryReceiptDTO.getRecipient());
        }

        //If updated entry receipt has registrations then delete the old ones for given receipt
        //update the quantity in product-shelf association table
        //and insert the new registrations
        if (entryReceiptDTO.getEntryRegistrationDTOList() != null && !entryReceiptDTO.getEntryRegistrationDTOList().isEmpty()){

            //update quantity in product-shelf association table for registrations
            //that are going to be deleted
            for (EntryRegistration entryRegistration : entryReceipt.getEntryRegistrations()) {
                productShelfService.updateProductInShelf(entryRegistration, "subtract");
            }

            //delete registrations for given receipt
            entryRegistrationService.deleteByReceiptId(entryReceiptDTO.getId());

            entryReceipt.getEntryRegistrations().clear();

            entryReceipt.setEntryRegistrations(fillEntryRegistrationList(entryReceiptDTO, entryReceipt));

            //For each registration update the appropriate entry in ProductShelf
            for (EntryRegistration entryRegistration: entryReceipt.getEntryRegistrations()){
                productShelfService.updateProductInShelf(entryRegistration, "add");
            }
        }

        entryReceiptRepository.save(entryReceipt);

        return entityToDTO(entryReceipt);
    }

    @Override
    public void delete(Long id) {
        entryReceiptValidator.validateDelete(id);

        EntryReceipt entryReceipt = entryReceiptRepository.getById(id);

        for (EntryRegistration entryRegistration: entryReceipt.getEntryRegistrations()){
            productShelfService.updateProductInShelf(entryRegistration, "subtract");
        }

        entryReceiptRepository.deleteById(id);
    }

    @Override
    public List<EntryReceiptDTO> findAll() {

        List<EntryReceipt> entryReceiptList = entryReceiptRepository.findAll();

        List<EntryReceiptDTO> entryReceiptDTOList = new ArrayList<>();

        for (EntryReceipt entryReceipt: entryReceiptList){
            entryReceiptDTOList.add(entityToDTO(entryReceipt));
        }
        return entryReceiptDTOList;
    }

    @Override
    public EntryReceiptDTO findById(Long id) {
        EntryReceiptDTO entryReceiptDTO = entityToDTO(entryReceiptRepository.getById(id));
        entryReceiptDTO.setEntryRegistrationDTOList(entryRegistrationService.getByReceiptId(id));
        return entryReceiptDTO;
    }


}
