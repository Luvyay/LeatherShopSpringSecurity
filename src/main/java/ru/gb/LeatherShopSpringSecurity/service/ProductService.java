package ru.gb.LeatherShopSpringSecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.LeatherShopSpringSecurity.model.Product;
import ru.gb.LeatherShopSpringSecurity.repository.ProductRepository;

import java.util.List;

/**
 * Класс реализующий взаимодействие с репозиторием по товарам
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    /**
     * Метод для получения всех товаров, даже если у них количество (quantity) равно 0
     * @return
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Метод для получения всех товаров у которых количество (quantity) больше 0
     * @return
     */
    public List<Product> getAllProductsWhereQuantityMoreZero() {
        return productRepository.findAllWhereQuantityMoreZero();
    }

    /**
     * Метод по получению товара по id
     * @param id
     * @return
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    /**
     * Метод по созданию товара
     * @param product
     * @return
     */
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Метод по обновлению данных у товара
     * @param product
     * @return
     */
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Мето по удалению товара по id
     * @param id
     */
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * Метод по уменьшению количество (quantity) у товара по id
     * @param id
     */
    public void decreaseQuantityById(Long id) {
        Product findProduct = productRepository.findById(id).orElse(null);

        if (findProduct != null) {
            findProduct.setQuantity(findProduct.getQuantity() - 1);
            updateProduct(findProduct);
        }
    }
}
