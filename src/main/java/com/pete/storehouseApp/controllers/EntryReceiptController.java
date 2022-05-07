package com.pete.storehouseApp.controllers;

import com.pete.storehouseApp.dto.EntryReceiptDTO;
import com.pete.storehouseApp.services.EntryReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/receipt/entry")
public class EntryReceiptController {

    @Autowired
    EntryReceiptService entryReceiptService;

    @PostMapping("/create")
    public EntryReceiptDTO create(@RequestBody EntryReceiptDTO entryReceiptDTO){

        entryReceiptService.create(entryReceiptDTO);

        return entryReceiptDTO;
    }

    @PutMapping("/update")
    public EntryReceiptDTO update(@RequestBody EntryReceiptDTO entryReceiptDTO){

        entryReceiptService.update(entryReceiptDTO);

        return entryReceiptDTO;
    }

    @GetMapping("/all")
    public List<EntryReceiptDTO> findAll(){

        return entryReceiptService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(value = "id") Long id){
        entryReceiptService.delete(id);
    }

    @GetMapping("/{id}")
    public EntryReceiptDTO findById(@PathVariable(value = "id") Long id){
        return entryReceiptService.findById(id);
    }

}
