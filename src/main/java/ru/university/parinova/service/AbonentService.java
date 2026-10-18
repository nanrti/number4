package ru.university.parinova.service;

import ru.university.parinova.model.Abonent;
import ru.university.parinova.repository.AbonentRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AbonentService {
    private final AbonentRepository repository;

    public AbonentService(AbonentRepository repository) {
        this.repository = repository;
    }

    public Abonent addAbonent(String surname, String name, List<String> phones) {
        Abonent abonent = new Abonent(surname, name, phones);
        return repository.add(abonent);
    }

    public boolean deleteAbonent(int id) {
        return repository.deleteById(id);
    }

    public boolean editAbonent(int id, String surname, String name, List<String> phones) {
        Optional<Abonent> found = repository.findById(id);
        if (found.isEmpty()) {
            return false;
        }
        Abonent abonent = found.get();
        if (surname != null) abonent.setSurname(surname);
        if (name != null) abonent.setName(name);
        if (phones != null) abonent.setPhones(phones);
        return true;
    }

    public Optional<Abonent> getById(int id) {
        return repository.findById(id);
    }

    private static final Comparator<Abonent> COMPARATOR = (a, b) -> {
        int result = a.getSurname().compareToIgnoreCase(b.getSurname());
        if (result != 0) return result;

        return a.getName().compareToIgnoreCase(b.getName());
    };

    public List<Abonent> getAllSorted() {
        List<Abonent> list = repository.findAll();
        list.sort(COMPARATOR);
        return list;
    }
}