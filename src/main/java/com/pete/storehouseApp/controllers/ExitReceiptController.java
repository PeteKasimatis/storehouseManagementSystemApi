package com.pete.storehouseApp.controllers;

import com.pete.storehouseApp.dto.ExitReceiptDTO;
import com.pete.storehouseApp.services.ExitReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/receipt/exit")
public class ExitReceiptController {

    @Autowired
    ExitReceiptService exitReceiptService;

    @PostMapping("/create")
    public ExitReceiptDTO create(@RequestBody ExitReceiptDTO exitReceiptDTO){

        exitReceiptService.create(exitReceiptDTO);

        return exitReceiptDTO;
    }

    @PutMapping("/update")
    public ExitReceiptDTO update(@RequestBody ExitReceiptDTO exitReceiptDTO){

        exitReceiptService.update(exitReceiptDTO);

        return exitReceiptDTO;
    }

    @GetMapping("/all")
    public List<ExitReceiptDTO> findAll(){

        return exitReceiptService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(value = "id") Long id){
        exitReceiptService.delete(id);
    }

    @GetMapping("/{id}")
    public ExitReceiptDTO findById(@PathVariable(value = "id") Long id){
        return exitReceiptService.findById(id);
    }
}
