package com.workintech.s18d1.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="burger", schema = "public")
public class Burger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="price")
    private double price;
    @Column(name="is_vegan")
    private boolean isVegan;
    @Column(name="bread_type")
    private BreadType breadType;
    @Column(name="content")
    private String contents;




    public boolean getIsVegan() {
        return false;
    }

    public void setIsVegan(boolean b) {
    }
}
