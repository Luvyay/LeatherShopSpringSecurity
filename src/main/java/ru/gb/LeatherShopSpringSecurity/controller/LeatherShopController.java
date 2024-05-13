package ru.gb.LeatherShopSpringSecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.gb.LeatherShopSpringSecurity.model.BoughtProducts;
import ru.gb.LeatherShopSpringSecurity.model.Product;
import ru.gb.LeatherShopSpringSecurity.service.BoughtProductsService;
import ru.gb.LeatherShopSpringSecurity.service.ProductService;
import ru.gb.LeatherShopSpringSecurity.service.securityService.UserService;

import java.util.List;

/**
 * Класс по адресации на основном сайте
 */
@Controller
@RequiredArgsConstructor
public class LeatherShopController {
    private final ProductService productService;
    private final BoughtProductsService boughtProductsService;
    private final UserService userService;

    /**
     * Метод по отображению главной страницы со всеми товарами у которых количество (quantity) больше 0
     * @param product
     * @param model
     * @return
     */
    @GetMapping("/")
    public String getHome(Product product, Model model) {
        List<Product> allProductsWithQuantityMoreZero = productService.getAllProductsWhereQuantityMoreZero();

        // передача полученных товаров
        model.addAttribute("products", allProductsWithQuantityMoreZero);

        return "home";
    }

    /**
     * Метод по уменьшению количества (quantity) у товара и добавления новой записи в таблицу bought_products
     * @param id
     * @return
     */
    @GetMapping("/buy/{id}")
    public String decreaseQuantity(@PathVariable(name="id") Long id) {
        // получение пользователя
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        // получение id пользователя
        Long idUser = userService.findUserByName(userDetails.getUsername()).getId();

        // уменьшение количества (quantity) у товара на 1
        productService.decreaseQuantityById(id);

        // добавление BoughtProducts (т.е. айди пользователя и айди купленного товара
        // для отслеживания кто какой товар купил)
        boughtProductsService.addBoughtProducts(new BoughtProducts(idUser, id));

        return "redirect:/";
    }

    /**
     * Метод по отображению профиля пользователя
     * На странице отображается информация о пользователе (логин и его роль) и купленные товары
     * @param model
     * @return
     */
    @GetMapping("/profile")
    public String getUser(Model model) {
        // получение пользователя
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        // получение id пользователя
        Long idUser = userService.findUserByName(userDetails.getUsername()).getId();

        // получение списка купленных товаров (BoughtProducts) конкретного пользователя
        List<BoughtProducts> boughtProducts = boughtProductsService.getAllBoughtProductsByUserId(idUser);
        // конвертирование списка купленных товаров (BoughtProducts) конкретного пользователя в список товаров
        List<Product> products = boughtProducts.stream()
                        .map(bought -> productService.getProductById(bought.getIdProduct()))
                                .toList();

        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("products", products);

        return "profile";
    }
}
