package com.recipebook.www.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping
    public String runApp() {
        return "Приложение запущено";
    }

    @GetMapping(path = "/info")
    public String getInfo() {
        return """
                Имя ученика: Герасименко Максим Анатольевич
                Название проекта: Книга рецептов Чака Норриса
                Дата создания: 07.02.2024
                Описание проекта: В книге собраны рецепты блюд, благодаря которым
                 ты будешь как Чак Норрис =)""";
    }



}
