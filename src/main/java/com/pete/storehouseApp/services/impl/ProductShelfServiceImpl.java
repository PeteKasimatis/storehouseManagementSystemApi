package com.pete.storehouseApp.services.impl;

import com.pete.storehouseApp.dto.ProductShelfDTO;
import com.pete.storehouseApp.models.EntryRegistration;
import com.pete.storehouseApp.models.ExitRegistration;
import com.pete.storehouseApp.models.ProductShelf;
import com.pete.storehouseApp.models.ProductShelfId;
import com.pete.storehouseApp.repositories.ProductRepository;
import com.pete.storehouseApp.repositories.ProductShelfRepository;
import com.pete.storehouseApp.repositories.ShelfRepository;
import com.pete.storehouseApp.services.ProductShelfService;
import com.pete.storehouseApp.validators.ProductShelfValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductShelfServiceImpl implements ProductShelfService {

    @Autowired
    ProductShelfRepository productShelfRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShelfRepository shelfRepository;

    @Autowired
    ProductShelfValidator productShelfValidator;


    private ProductShelf dtoToEntity(ProductShelfDTO productShelfDTO){

        ProductShelf productShelf = new ProductShelf();

        BeanUtils.copyProperties(productShelfDTO, productShelf);

        productShelf.setProduct(productRepository.findByBarcode(productShelfDTO.getProductBarcode()));

        productShelf.setShelf(shelfRepository.findByIdentifier(productShelfDTO.getShelfIdentifier()));

        return productShelf;
    }

    private ProductShelfDTO entityToDTO(ProductShelf productShelf){

        ProductShelfDTO productShelfDTO = new ProductShelfDTO();

        BeanUtils.copyProperties(productShelf, productShelfDTO);

        productShelfDTO.setProductBarcode(productShelf.getProduct().getBarcode());

        productShelfDTO.setShelfIdentifier(productShelf.getShelf().getIdentifier());

        return productShelfDTO;
    }


    @Override
    public List<ProductShelfDTO> findAll() {

        List<ProductShelf> productShelfList =  productShelfRepository.findAll();

        List<ProductShelfDTO> productShelfDTOList = new ArrayList<>();

        for (ProductShelf productShelf: productShelfList){
            productShelfDTOList.add(entityToDTO(productShelf));
        }

        return productShelfDTOList;
    }

    @Override
    public List<ProductShelfDTO> findByBarcode(String barcode) {

        productShelfValidator.validateBarcode(barcode);

        List<ProductShelf> productShelfList = productShelfRepository.findByBarcode(barcode);

        List<ProductShelfDTO> productShelfDTOList = new ArrayList<>();

        for (ProductShelf productShelf: productShelfList){
            productShelfDTOList.add(entityToDTO(productShelf));
        }

        return productShelfDTOList;
    }

    @Override
    public List<ProductShelfDTO> findByIdentifier(String identifier) {

        productShelfValidator.validateIdentifier(identifier);

        List<ProductShelf> productShelfList = productShelfRepository.findByIdentifier(identifier);

        List<ProductShelfDTO> productShelfDTOList = new ArrayList<>();

        for (ProductShelf productShelf: productShelfList){
            productShelfDTOList.add(entityToDTO(productShelf));
        }

        return productShelfDTOList;
    }

    @Override
    public void updateProductInShelf(EntryRegistration entryRegistration, String action) {

        ProductShelfId productShelfId = new ProductShelfId(entryRegistration.getProduct().getId(),entryRegistration.getShelf().getId());

        ProductShelf productShelf = new ProductShelf();

        //If association already exists update quantity else create the association
        if (productShelfRepository.existsById(productShelfId)) {
            productShelf = productShelfRepository.getById(productShelfId);

            if (action.equals("add"))
                productShelf.setQuantity(productShelf.getQuantity() + entryRegistration.getQuantity());
            else if (action.equals("subtract"))
                productShelf.setQuantity(productShelf.getQuantity() - entryRegistration.getQuantity());

        }
        else {
            productShelf.setShelf(entryRegistration.getShelf());
            productShelf.setProduct(entryRegistration.getProduct());
            productShelf.setQuantity(entryRegistration.getQuantity());
        }

        //in case we subtract from the quantity and the new value is 0 delete the entry
        //from the table since the given shelf does not contain the given product any more
        //else save the association
        if (productShelf.getQuantity() == 0)
            productShelfRepository.delete(productShelf);
        else
            productShelfRepository.save(productShelf);
    }

    @Override
    public void updateProductInShelf(ExitRegistration exitRegistration, String action) {
        ProductShelfId productShelfId = new ProductShelfId(exitRegistration.getProduct().getId(),exitRegistration.getShelf().getId());

        ProductShelf productShelf = new ProductShelf();

        //If association already exists update quantity else create the association
        if (productShelfRepository.existsById(productShelfId)) {
            productShelf = productShelfRepository.getById(productShelfId);

            if (action.equals("add"))
                productShelf.setQuantity(productShelf.getQuantity() + exitRegistration.getQuantity());
            else if (action.equals("subtract"))
                productShelf.setQuantity(productShelf.getQuantity() - exitRegistration.getQuantity());

        }
        else {
            productShelf.setShelf(exitRegistration.getShelf());
            productShelf.setProduct(exitRegistration.getProduct());
            productShelf.setQuantity(exitRegistration.getQuantity());
        }
        //in case we subtract from the quantity and the new value is 0 delete the entry
        //from the table since the given shelf does not contain the given product any more
        //else save the association
        if (productShelf.getQuantity() == 0)
            productShelfRepository.delete(productShelf);
        else
            productShelfRepository.save(productShelf);
    }


}
