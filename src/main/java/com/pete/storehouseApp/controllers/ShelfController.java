package com.pete.storehouseApp.controllers;

import com.pete.storehouseApp.dto.ShelfDTO;
import com.pete.storehouseApp.services.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/shelf")
public class ShelfController {

    @Autowired
    ShelfService shelfService;


    @PostMapping("/create")
    public ShelfDTO createShelf(@RequestBody ShelfDTO shelfDTO){

        shelfService.create(shelfDTO);

        return shelfDTO;
    }

    @PutMapping("/update")
    public ShelfDTO updateShelf(@RequestBody ShelfDTO shelfDTO){

        shelfDTO = shelfService.update(shelfDTO);

        return shelfDTO;
    }

    @GetMapping("/all")
    public List<ShelfDTO> findAll(){

        return shelfService.findAll();

    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable(value = "id") Long id){

        shelfService.delete(id);
    }

    @GetMapping("/find/{identifier}")
    public ShelfDTO findByIdentifier(@PathVariable(value = "identifier") String identifier){

        return shelfService.findByIdentifier(identifier);
    }
}
