package com.workintech.s18d1.controller;


import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.dao.BurgerDaoImpl;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.util.BurgerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workintech")
public class BurgerController {

    private final BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDaoImpl burgerDao) {
        this.burgerDao = burgerDao;
    }

    @PostMapping("/burgers")
    public Burger save(@RequestBody Burger burger){

        BurgerValidation.checkName(burger.getName());
        return burgerDao.save(burger);
    }

    @GetMapping("/burgers")
    public List<Burger> findAll(){
        return burgerDao.findAll();
    }
    @GetMapping("/burgers/{id}")
    public Burger burgerById(@PathVariable long id){
        return burgerDao.findById(id);
    }
    @PutMapping("/burgers/{id}")
    public Burger update(@PathVariable long id,@RequestBody Burger burger){
        BurgerValidation.checkName(burger.getName());
        burger.setId(id);
        return burgerDao.update(burger);

    }
    @DeleteMapping("/burgers/{id}")
    public Burger delete(@PathVariable long id){
        return burgerDao.remove(id);
    }
    @GetMapping("/burgers/findByPrice")
    public List<Burger> findByPrice(@RequestBody double price){
        return burgerDao.findByPrice(price);
    }
    @GetMapping("/burgers/findByBreadType ")
    public List<Burger> findByBreadType(@RequestBody BreadType breadType){
        return burgerDao.findByBreadType(breadType);
    }@GetMapping("/burgers/findByContent ")
    public List<Burger> findByContent(@RequestBody String contents){
        return burgerDao.findByContent(contents);
    }
}
