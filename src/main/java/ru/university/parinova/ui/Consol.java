package ru.university.parinova.ui;

import ru.university.parinova.model.Abonent;
import ru.university.parinova.service.AbonentService;

import java.util.List;
import java.util.Scanner;

public class Consol {
    private final AbonentService service;
    private final Scanner scanner = new Scanner(System.in);

    public Consol(AbonentService service) {
        this.service = service;
    }

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> showAll();
                    case "0" -> running = false;
                    default -> System.out.println("Неверный пункт меню");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        System.out.println("До свидания!");
    }

    private void printMenu() {
        System.out.println();
        System.out.println("Телефонный справочник:");
        System.out.println("1. Показать всех абонентов");
        System.out.println("0. Выход");
        System.out.print("Выбор: ");
    }

    private void showAll() {
        printList(service.getAllSorted());
    }

    private void printList(List<Abonent> list) {
        if (list.isEmpty()) {
            System.out.println("Ничего не найдено");
            return;
        }
        for (Abonent a : list) {
            System.out.println(a);
        }
    }
}