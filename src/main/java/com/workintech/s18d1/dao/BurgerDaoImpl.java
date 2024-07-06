package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BurgerDaoImpl implements BurgerDao{

    private EntityManager entityManager;

    @Autowired
    public BurgerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Burger save(Burger burger) {

        entityManager.persist(burger);
       return burger;
    }
    @Transactional
    @Override
    public Burger findById(long id) {
        Burger burger= entityManager.find(Burger.class,id);
        if(burger == null){
            throw new BurgerException("Burger not Found: "+id, HttpStatus.NOT_FOUND);
        }
        return burger;
    }
    @Transactional
    @Override
    public List<Burger> findAll() {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b",Burger.class);

        return query.getResultList();
    }
    @Transactional
    @Override
    public List<Burger> findByPrice(double price) {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b WHERE b.price>:price ORDER BY b.price DESC",Burger.class);
        query.setParameter("price",price);
        return query.getResultList();

    }
    @Transactional
    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b WHERE b.breadType = :breadType ORDER BY b.name ASC", Burger.class);
        query.setParameter("breadType", breadType);
        return query.getResultList();
    }
    @Transactional
    @Override
    public List<Burger> findByContent(String contents) {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b WHERE b.content LIKE CONCAT('%',:content,'%') ORDER BY b.name", Burger.class);
        query.setParameter("content",contents);
        return query.getResultList();
    }
    @Transactional
    @Override
    public Burger update(Burger burger) {
    return entityManager.merge(burger);
    }
    @Transactional
    @Override
    public Burger remove(long id) {
    Burger foundBurder = entityManager.find(Burger.class,id);
    entityManager.remove(foundBurder);
    return foundBurder;
    }
}
