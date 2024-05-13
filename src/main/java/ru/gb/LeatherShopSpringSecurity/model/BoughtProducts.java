package ru.gb.LeatherShopSpringSecurity.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bought_products")
public class BoughtProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "id_product")
    private Long idProduct;

    public BoughtProducts() {
    }

    public BoughtProducts(Long idUser, Long idProduct) {
        this.idUser = idUser;
        this.idProduct = idProduct;
    }
}
