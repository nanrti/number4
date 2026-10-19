package ru.university.parinova.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.university.parinova.model.Abonent;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AbonentRepositoryTest {
    private AbonentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new AbonentRepository();
    }

    private Abonent newAbonent(String surname) {
        return new Abonent(surname, "Иван", List.of("111"));
    }

    @Test
    void newRepositoryIsEmpty() {
        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    void addAssignsIdStartingFromOne() {
        Abonent first = repository.add(newAbonent("Иванов"));
        Abonent second = repository.add(newAbonent("Петров"));

        assertEquals(1, first.getId());
        assertEquals(2, second.getId());
    }

    @Test
    void addReturnsSameObject() {
        Abonent a = newAbonent("Иванов");

        Abonent result = repository.add(a);

        assertSame(a, result);
    }

    @Test
    void findAllReturnsAllAddedAbonents() {
        repository.add(newAbonent("Иванов"));
        repository.add(newAbonent("Петров"));

        assertEquals(2, repository.findAll().size());
    }

    @Test
    void findAllReturnsCopyOfList() {
        repository.add(newAbonent("Иванов"));

        List<Abonent> list = repository.findAll();
        list.clear();

        assertEquals(1, repository.findAll().size());
    }

    @Test
    void findByIdReturnsExistingAbonent() {
        Abonent a = repository.add(newAbonent("Иванов"));

        Optional<Abonent> found = repository.findById(a.getId());

        assertTrue(found.isPresent());
        assertSame(a, found.get());
    }

    @Test
    void findByIdReturnsEmptyForUnknownId() {
        repository.add(newAbonent("Иванов"));

        assertTrue(repository.findById(99).isEmpty());
    }

    @Test
    void deleteByIdRemovesAbonent() {
        Abonent a = repository.add(newAbonent("Иванов"));

        boolean removed = repository.deleteById(a.getId());

        assertTrue(removed);
        assertTrue(repository.findAll().isEmpty());
        assertTrue(repository.findById(a.getId()).isEmpty());
    }

    @Test
    void deleteByIdReturnsFalseForUnknownId() {
        repository.add(newAbonent("Иванов"));

        assertFalse(repository.deleteById(99));
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void deleteByIdRemovesOnlyChosenAbonent() {
        Abonent first = repository.add(newAbonent("Иванов"));
        Abonent second = repository.add(newAbonent("Петров"));

        repository.deleteById(first.getId());

        List<Abonent> rest = repository.findAll();
        assertEquals(1, rest.size());
        assertSame(second, rest.get(0));
    }

    @Test
    void idsAreNotReusedAfterDelete() {
        Abonent first = repository.add(newAbonent("Иванов"));
        repository.deleteById(first.getId());

        Abonent second = repository.add(newAbonent("Петров"));

        assertEquals(2, second.getId());
    }
}