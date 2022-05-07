package com.pete.storehouseApp.controllers;

import com.pete.storehouseApp.dto.ProductShelfDTO;
import com.pete.storehouseApp.services.ProductShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/productshelf")
public class ProductShelfController {

    @Autowired
    ProductShelfService productShelfService;

    @GetMapping("/all")
    public List<ProductShelfDTO> findAll(){

        return productShelfService.findAll();
    }

    @GetMapping("/all/barcode/{barcode}")
    public List<ProductShelfDTO> findByBarcode(@PathVariable(value = "barcode") String barcode){

        return productShelfService.findByBarcode(barcode);
    }

    @GetMapping("/all/identifier/{identifier}")
    public List<ProductShelfDTO> findByIdentifier(@PathVariable(value = "identifier") String identifier){

        return productShelfService.findByIdentifier(identifier);
    }
}
