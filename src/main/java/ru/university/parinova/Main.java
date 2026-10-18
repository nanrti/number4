package ru.university.parinova;

import ru.university.parinova.model.Abonent;
import ru.university.parinova.repository.AbonentRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AbonentRepository repo = new AbonentRepository();
        repo.add(new Abonent("Паринова", "Анастасия", List.of("111")));
        repo.add(new Abonent("Брыковская", "София", List.of("222", "333")));
        System.out.println(repo.findAll());
        repo.deleteById(1);
        System.out.println(repo.findAll());
    }
}