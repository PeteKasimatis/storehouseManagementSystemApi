package com.pete.storehouseApp.controllers;

import com.pete.storehouseApp.dto.ProductDTO;
import com.pete.storehouseApp.dto.StockDTO;
import com.pete.storehouseApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;


    @PostMapping("/create")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){

        productService.create(productDTO);

        return productDTO;
    }

    @PutMapping("/update")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO){

        productDTO = productService.update(productDTO);

        return productDTO;
    }

    @GetMapping("/all")
    public List<ProductDTO> findAll(){

        return productService.findAll();

    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable(value = "id") Long id){

        productService.delete(id);
    }

    @GetMapping("/find/{barcode}")
    public ProductDTO findByBarcode(@PathVariable(value = "barcode") String barcode){

        return productService.findByBarcode(barcode);
    }

    @GetMapping("/stock/{barcode}/{date}")
    public List<StockDTO> getStockByBarcodeByDate(@PathVariable(value = "barcode") String barcode,
                                                  @PathVariable(value = "date")@DateTimeFormat(pattern = "dd-MM-yyyy") Date date){

        return productService.getStockByBarcodeByDate(barcode, date);
    }
}
