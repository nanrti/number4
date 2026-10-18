package ru.university.parinova.service;

import ru.university.parinova.model.Abonent;
import ru.university.parinova.repository.AbonentRepository;

import java.util.ArrayList;
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

        result = a.getName().compareToIgnoreCase(b.getName());
        if (result != 0) return result;

        List<String> p1 = new ArrayList<>(a.getPhones());
        List<String> p2 = new ArrayList<>(b.getPhones());
        p1.sort(String::compareTo);
        p2.sort(String::compareTo);

        int size = Math.min(p1.size(), p2.size());
        for (int i = 0; i < size; i++) {
            result = p1.get(i).compareTo(p2.get(i));
            if (result != 0) return result;
        }
        return Integer.compare(p1.size(), p2.size());
    };

    public List<Abonent> getAllSorted() {
        List<Abonent> list = repository.findAll();
        list.sort(COMPARATOR);
        return list;
    }
}