package com.pete.storehouseApp.services.impl;

import com.pete.storehouseApp.dto.EntryRegistrationDTO;
import com.pete.storehouseApp.models.EntryRegistration;
import com.pete.storehouseApp.repositories.EntryRegistrationRepository;
import com.pete.storehouseApp.repositories.ProductRepository;
import com.pete.storehouseApp.repositories.ShelfRepository;
import com.pete.storehouseApp.services.EntryRegistrationService;
import com.pete.storehouseApp.validators.EntryRegistrationValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntryRegistrationServiceImpl implements EntryRegistrationService {

    @Autowired
    EntryRegistrationValidator entryRegistrationValidator;

    @Autowired
    EntryRegistrationRepository entryRegistrationRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShelfRepository shelfRepository;

    @Override
    public EntryRegistration dtoToEntity(EntryRegistrationDTO entryRegistrationDTO) {

        EntryRegistration entryRegistration = new EntryRegistration();

        BeanUtils.copyProperties(entryRegistrationDTO, entryRegistration);

        entryRegistration.setProduct(productRepository.findByBarcode(entryRegistrationDTO.getProductBarcode()));

        entryRegistration.setShelf(shelfRepository.findByIdentifier(entryRegistrationDTO.getShelfIdentifier()));


        return entryRegistration;
    }

    @Override
    public EntryRegistrationDTO entityToDTO(EntryRegistration entryRegistration) {

        EntryRegistrationDTO entryRegistrationDTO = new EntryRegistrationDTO();

        BeanUtils.copyProperties(entryRegistration, entryRegistrationDTO);

        entryRegistrationDTO.setProductBarcode(entryRegistration.getProduct().getBarcode());

        entryRegistrationDTO.setShelfIdentifier(entryRegistration.getShelf().getIdentifier());

        return entryRegistrationDTO;
    }

    @Override
    public void deleteByReceiptId(Long id) {

        entryRegistrationRepository.deleteByReceiptId(id);

    }

    @Override
    public List<EntryRegistrationDTO> getByReceiptId(Long id) {
        List<EntryRegistration> entryRegistrationList = entryRegistrationRepository.getByReceiptId(id);

        List<EntryRegistrationDTO> entryRegistrationDTOList = new ArrayList<>();

        for (EntryRegistration entryRegistration: entryRegistrationList){
            entryRegistrationDTOList.add((entityToDTO(entryRegistration)));
        }
        return entryRegistrationDTOList;
    }


}
