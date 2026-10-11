package ru.university.parinova.model;

import java.util.List;

public class abonent {
    private int id;
    private String surname;
    private String name;
    private List<String> phones;

    public abonent(String surname, String name, List<String> phones) {
        this.surname = surname;
        this.name = name;
        this.phones = phones;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getPhones() { return phones; }
    public void setPhones(List<String> phones) { this.phones = phones; }

    @Override
    public String toString() {
        return "[" + id + "] " + surname + " " + name + " | тел.: " + String.join(", ", phones);
    }
}