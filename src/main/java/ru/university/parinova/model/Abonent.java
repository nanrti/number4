package ru.university.parinova.model;

import java.util.ArrayList;
import java.util.List;

public class Abonent {
    public static final int MAX_PHONES = 3;

    private int id;
    private String surname;
    private String name;
    private List<String> phones;

    public Abonent(String surname, String name, List<String> phones) {
        setSurname(surname);
        setName(name);
        setPhones(phones);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname == null || surname.trim().isEmpty()) {
            throw new IllegalArgumentException("Фамилия не может быть пустой");
        }
        this.surname = surname.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        this.name = name.trim();
    }

    public List<String> getPhones() {
        return new ArrayList<>(phones);
    }

    public void setPhones(List<String> phones) {
        if (phones == null || phones.isEmpty()) {
            throw new IllegalArgumentException("Нужен хотя бы один номер телефона");
        }
        if (phones.size() > MAX_PHONES) {
            throw new IllegalArgumentException("Не более " + MAX_PHONES + " номеров телефона");
        }
        List<String> cleaned = new ArrayList<>();
        for (String phone : phones) {
            if (phone == null || phone.trim().isEmpty()) {
                throw new IllegalArgumentException("Номер телефона не может быть пустым");
            }
            cleaned.add(phone.trim());
        }
        this.phones = cleaned;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + surname + " " + name + " | тел.: " + String.join(", ", phones);
    }
}