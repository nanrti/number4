package ru.university.parinova;

import ru.university.parinova.repository.AbonentRepository;
import ru.university.parinova.service.AbonentService;
import ru.university.parinova.Controller.Consol;

public class Main {
    public static void main(String[] args) {
        AbonentRepository repository = new AbonentRepository();
        AbonentService service = new AbonentService(repository);
        Consol consol = new Consol(service);
        consol.start();
    }
}