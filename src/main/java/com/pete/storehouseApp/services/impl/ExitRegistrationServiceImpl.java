package com.pete.storehouseApp.services.impl;

import com.pete.storehouseApp.dto.ExitRegistrationDTO;
import com.pete.storehouseApp.models.ExitRegistration;
import com.pete.storehouseApp.repositories.ExitRegistrationRepository;
import com.pete.storehouseApp.repositories.ProductRepository;
import com.pete.storehouseApp.repositories.ShelfRepository;
import com.pete.storehouseApp.services.ExitRegistrationService;
import com.pete.storehouseApp.validators.ExitRegistrationValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExitRegistrationServiceImpl implements ExitRegistrationService {

    @Autowired
    ExitRegistrationValidator exitRegistrationValidator;

    @Autowired
    ExitRegistrationRepository exitRegistrationRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShelfRepository shelfRepository;

    @Override
    public ExitRegistration dtoToEntity(ExitRegistrationDTO exitRegistrationDTO) {
        ExitRegistration exitRegistration = new ExitRegistration();

        BeanUtils.copyProperties(exitRegistrationDTO, exitRegistration);

        exitRegistration.setProduct(productRepository.findByBarcode(exitRegistrationDTO.getProductBarcode()));

        exitRegistration.setShelf(shelfRepository.findByIdentifier(exitRegistrationDTO.getShelfIdentifier()));

        return exitRegistration;
    }

    @Override
    public ExitRegistrationDTO entityToDTO(ExitRegistration exitRegistration) {

        ExitRegistrationDTO exitRegistrationDTO = new ExitRegistrationDTO();

        BeanUtils.copyProperties(exitRegistration, exitRegistrationDTO);

        exitRegistrationDTO.setProductBarcode(exitRegistration.getProduct().getBarcode());

        exitRegistrationDTO.setShelfIdentifier(exitRegistration.getShelf().getIdentifier());

        return exitRegistrationDTO;
    }

    @Override
    public void deleteByReceiptId(Long id) {

        exitRegistrationRepository.deleteByReceiptId(id);
    }

    @Override
    public List<ExitRegistrationDTO> getByReceiptId(Long id) {
        List<ExitRegistration> exitRegistrationList = exitRegistrationRepository.getByReceiptId(id);

        List<ExitRegistrationDTO> exitRegistrationDTOList = new ArrayList<>();

        for (ExitRegistration exitRegistration: exitRegistrationList){
            exitRegistrationDTOList.add((entityToDTO(exitRegistration)));
        }
        return exitRegistrationDTOList;
    }
}
