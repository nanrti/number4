package ru.university.parinova.repository;
import ru.university.parinova.model.Abonent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AbonentRepository {
    private final List<Abonent> abonents = new ArrayList<>();
    private int nextId = 1;

    public Abonent add(Abonent abonent) {
        abonent.setId(nextId++);
        abonents.add(abonent);
        return abonent;
    }

    public List<Abonent> findAll() {
        return new ArrayList<>(abonents);
    }

    public Optional<Abonent> findById(int id) {
        for (Abonent a : abonents) {
            if (a.getId() == id) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

    public boolean deleteById(int id) {
        return abonents.removeIf(a -> a.getId() == id);
    }
}