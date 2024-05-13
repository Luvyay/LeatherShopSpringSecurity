package ru.gb.LeatherShopSpringSecurity.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = false, name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false, name = "short_description", columnDefinition = "TEXT")
    private String shortDescription;
    @Column(name = "url_photo", columnDefinition = "TEXT")
    private String urlPhoto;
    @Column(name = "price")
    private int price;
    @Column(name = "quantity")
    private int quantity;
}
