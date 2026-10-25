package ru.university.parinova.Controller;

import ru.university.parinova.exception.BusinessException;
import ru.university.parinova.model.Abonent;
import ru.university.parinova.service.AbonentService;

import java.util.ArrayList;
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
                    case "2" -> addAbonent();
                    case "3" -> editAbonent();
                    case "4" -> deleteAbonent();
                    case "5" -> searchBySurname();
                    case "6" -> searchByPhone();
                    case "0" -> running = false;
                    default -> System.out.println("Неверный пункт меню");
                }
            } catch (BusinessException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        System.out.println("До свидания!");
    }

    private void printMenu() {
        System.out.println();
        System.out.println("Телефонный справочник:");
        System.out.println("1. Показать всех абонентов");
        System.out.println("2. Добавить абонента");
        System.out.println("3. Редактировать абонента");
        System.out.println("4. Удалить абонента");
        System.out.println("5. Поиск по фамилии");
        System.out.println("6. Поиск по номеру телефона");
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

    private void addAbonent() {
        System.out.print("Фамилия: ");
        String surname = scanner.nextLine();
        System.out.print("Имя: ");
        String name = scanner.nextLine();
        List<String> phones = readPhones();

        Abonent a = service.addAbonent(surname, name, phones);
        System.out.println("Добавлен: " + a);
    }

    private List<String> readPhones() {
        System.out.print("Номера телефонов (через пробел или запятую, до 3): ");
        return parsePhones(scanner.nextLine());
    }

    private List<String> parsePhones(String line) {
        List<String> phones = new ArrayList<>();
        for (String part : line.split("[,\\s]+")) {
            if (!part.isEmpty()) {
                phones.add(part);
            }
        }
        return phones;
    }

    private int readId() {
        System.out.print("Введите id абонента: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("id должен быть числом");
            return -1;
        }
    }

    private void deleteAbonent() {
        int id = readId();
        if (id == -1) return;

        service.deleteAbonent(id);
        System.out.println("Абонент удалён");
    }

    private void editAbonent() {
        int id = readId();
        if (id == -1) return;

        Abonent found = service.getById(id);
        System.out.println("Текущие данные: " + found);
        System.out.println("(Пустая строка - оставить без изменений)");

        System.out.print("Новая фамилия: ");
        String surname = scanner.nextLine();
        System.out.print("Новое имя: ");
        String name = scanner.nextLine();
        System.out.print("Новые номера (через пробел или запятую, до 3): ");
        String phonesLine = scanner.nextLine();

        List<String> phones = phonesLine.trim().isEmpty() ? null : parsePhones(phonesLine);

        service.editAbonent(
                id,
                surname.trim().isEmpty() ? null : surname,
                name.trim().isEmpty() ? null : name,
                phones
        );
        System.out.println("Данные обновлены");
    }



    private void searchBySurname() {
        System.out.print("Введите фамилию: ");
        printList(service.findBySurname(scanner.nextLine()));
    }

    private void searchByPhone() {
        System.out.print("Введите номер телефона: ");
        printList(service.findByPhone(scanner.nextLine()));
    }
}