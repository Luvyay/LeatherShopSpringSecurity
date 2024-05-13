package ru.gb.LeatherShopSpringSecurity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.LeatherShopSpringSecurity.model.securityModel.User;
import ru.gb.LeatherShopSpringSecurity.service.securityService.UserService;

/**
 * Класс по адресации страницы для регистрации
 */
@Controller
@AllArgsConstructor
public class RegistrationController {
    private UserService userService;

    /**
     * Метод по отображению страницы для регистрации
     * @param user
     * @param model
     * @return
     */
    @GetMapping("/registration")
    public String registration(User user, Model model) {

        return "registration";
    }

    /**
     * Метод по добавлению пользователя с дальнейшей переадресацией на страницу /login
     * @param user
     * @param model
     * @return
     */
    @PostMapping("/registration")
    public String adduser(User user, Model model) {
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }

        if (!userService.saveUser(user)) {
            model.addAttribute("userNameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/login";
    }
}
