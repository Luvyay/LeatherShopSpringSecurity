package ru.gb.LeatherShopSpringSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gb.LeatherShopSpringSecurity.model.BoughtProducts;

import java.util.List;

@Repository
public interface BoughtProductsRepository extends JpaRepository<BoughtProducts, Long> {
}
