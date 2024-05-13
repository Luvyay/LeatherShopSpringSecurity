package ru.gb.LeatherShopSpringSecurity.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.LeatherShopSpringSecurity.model.BoughtProducts;
import ru.gb.LeatherShopSpringSecurity.repository.BoughtProductsRepository;

import java.util.List;

/**
 * Класс реализующий взаимодействие с репозиторием по проданным товарам
 */
@Service
@AllArgsConstructor
public class BoughtProductsService {
    private BoughtProductsRepository boughtProductsRepository;
    @PersistenceContext
    private EntityManager em;

    /**
     * Метод по добавлению купленного товара
     * @param boughtProducts
     * @return
     */
    public BoughtProducts addBoughtProducts(BoughtProducts boughtProducts) {
        return boughtProductsRepository.save(boughtProducts);
    }

    /**
     * Метод по получению всех купленных товаров у конкретного пользователя по id
     * Используется собственный запрос: SELECT u FROM BoughtProducts u WHERE u.idUser = :paramId
     * Что обозначает: SELECT * FROM bought_products WHERE id_user = idUser; где idUser - передаваемое значение в методе
     * @param idUser
     * @return
     */
    public List<BoughtProducts> getAllBoughtProductsByUserId(Long idUser) {
        return em.createQuery("SELECT u FROM BoughtProducts u WHERE u.idUser = :paramId", BoughtProducts.class)
                .setParameter("paramId", idUser).getResultList();
    }
}
