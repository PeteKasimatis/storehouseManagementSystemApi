package com.pete.storehouseApp.controllers;

import com.pete.storehouseApp.dto.ShelfDTO;
import com.pete.storehouseApp.dto.StorehouseDTO;
import com.pete.storehouseApp.services.StorehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/storehouse")
public class StorehouseController {

    @Autowired
    StorehouseService storehouseService;


    @PostMapping("/create")
    public StorehouseDTO createStorehouse(@RequestBody StorehouseDTO storehouseDTO){

        return storehouseService.create(storehouseDTO);
    }


    @PutMapping("/update")
    public StorehouseDTO updateStorehouse(@RequestBody StorehouseDTO storehouseDTO){

        return storehouseService.update(storehouseDTO);
    }

    @GetMapping("/all")
    public List<StorehouseDTO> findAll(){

        return storehouseService.findAll();

    }

    @GetMapping("/{id}")
    public StorehouseDTO findStorehouse(@PathVariable(value = "id") Long id){

        return storehouseService.findById(id);

    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable(value = "id") Long id){

        storehouseService.delete(id);
    }

    //get all shelves of given storehouse
    @GetMapping("/shelves/{id}")
    public List<ShelfDTO> findAllShelvesByStorehouseId(@PathVariable(value = "id") Long id){

        return storehouseService.findAllShelves(id);
    }
}
