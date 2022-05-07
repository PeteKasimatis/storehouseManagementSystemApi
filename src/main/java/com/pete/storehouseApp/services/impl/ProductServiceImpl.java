package com.pete.storehouseApp.services.impl;

import com.pete.storehouseApp.dto.ProductDTO;
import com.pete.storehouseApp.dto.StockDTO;
import com.pete.storehouseApp.models.Product;
import com.pete.storehouseApp.repositories.ProductRepository;
import com.pete.storehouseApp.services.ProductService;
import com.pete.storehouseApp.validators.ProductValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductValidator productValidator;

    private Product dtoToEntity(ProductDTO productDTO) {

        Product product = new Product();

        BeanUtils.copyProperties(productDTO, product);

        return product;
    }

    private ProductDTO entityToDTO(Product product){

        ProductDTO productDTO = new ProductDTO();

        BeanUtils.copyProperties(product, productDTO);

        return productDTO;
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {

        productValidator.validateCreate(productDTO);

        Product product = dtoToEntity(productDTO);

        productRepository.save(product);

        productDTO.setId(product.getId());
        return productDTO;
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) {

        productValidator.validateUpdate(productDTO);

        Product product = productRepository.getById(productDTO.getId());

        if (productDTO.getBarcode() != null && !productDTO.getBarcode().isEmpty()){
            product.setBarcode(productDTO.getBarcode());
        }

        if (productDTO.getDescription() != null && !productDTO.getDescription().isEmpty()){
            product.setDescription(productDTO.getDescription());
        }

        if (productDTO.getUnits() != null && !productDTO.getUnits().isEmpty()){
            product.setUnits(productDTO.getUnits());
        }

        productRepository.save(product);

        return entityToDTO(product);
    }

    @Override
    public List<ProductDTO> findAll() {
        List<Product> productList = this.productRepository.findAll();

        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product: productList){
            productDTOList.add(entityToDTO(product));
        }

        return productDTOList;
    }

    @Override
    public void delete(Long id) {
        if (id != null){
            productValidator.validateDelete(id);

            productRepository.deleteById(id);
        }
    }

    @Override
    public ProductDTO findByBarcode(String barcode) {

        productValidator.validateFindByBarcode(barcode);

        ProductDTO productDTO = entityToDTO(productRepository.findByBarcode(barcode));

        return productDTO;
    }

    @Override
    public List<StockDTO> getStockByBarcodeByDate(String barcode, Date date) {

        productValidator.validateGetStock(barcode);

        Long productId = productRepository.findByBarcode(barcode).getId();

        return productRepository.getStockByBarcodeByDate(productId, date);
    }

}
