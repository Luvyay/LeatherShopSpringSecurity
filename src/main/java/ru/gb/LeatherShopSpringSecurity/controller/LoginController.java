package ru.gb.LeatherShopSpringSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Класс по адресации страницы для входа
 */
@Controller
public class LoginController {
    /**
     * Метод по отображению страницы для входа
     * @return
     */
    @GetMapping("/login")
    public String getPageLogin() {
        return "login";
    }
}
