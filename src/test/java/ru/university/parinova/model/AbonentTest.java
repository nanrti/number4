package ru.university.parinova.model;
import ru.university.parinova.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AbonentTest {

    @Test
    void createsAbonentWithValidData() {
        Abonent a = new Abonent("Иванов", "Иван", List.of("111", "222"));

        assertEquals("Иванов", a.getSurname());
        assertEquals("Иван", a.getName());
        assertEquals(List.of("111", "222"), a.getPhones());
    }

    @Test
    void trimsSpacesInFields() {
        Abonent a = new Abonent("  Иванов ", " Иван  ", List.of(" 111 "));

        assertEquals("Иванов", a.getSurname());
        assertEquals("Иван", a.getName());
        assertEquals(List.of("111"), a.getPhones());
    }

    @Test
    void allowsExactlyThreePhones() {
        Abonent a = new Abonent("Иванов", "Иван", List.of("1", "2", "3"));

        assertEquals(3, a.getPhones().size());
    }

    @Test
    void rejectsEmptySurname() {
        assertThrows(BusinessException.class,
                () -> new Abonent("", "Иван", List.of("111")));
    }

    @Test
    void rejectsBlankSurname() {
        assertThrows(BusinessException.class,
                () -> new Abonent("   ", "Иван", List.of("111")));
    }

    @Test
    void rejectsNullSurname() {
        assertThrows(BusinessException.class,
                () -> new Abonent(null, "Иван", List.of("111")));
    }

    @Test
    void rejectsEmptyName() {
        assertThrows(BusinessException.class,
                () -> new Abonent("Иванов", "", List.of("111")));
    }

    @Test
    void rejectsNullName() {
        assertThrows(BusinessException.class,
                () -> new Abonent("Иванов", null, List.of("111")));
    }

    @Test
    void rejectsNullPhones() {
        assertThrows(BusinessException.class,
                () -> new Abonent("Иванов", "Иван", null));
    }

    @Test
    void rejectsEmptyPhonesList() {
        assertThrows(BusinessException.class,
                () -> new Abonent("Иванов", "Иван", List.of()));
    }

    @Test
    void rejectsMoreThanThreePhones() {
        assertThrows(BusinessException.class,
                () -> new Abonent("Иванов", "Иван", List.of("1", "2", "3", "4")));
    }

    @Test
    void rejectsBlankPhone() {
        assertThrows(BusinessException.class,
                () -> new Abonent("Иванов", "Иван", List.of("111", "  ")));
    }

    @Test
    void setSurnameChangesSurname() {
        Abonent a = new Abonent("Иванов", "Иван", List.of("111"));

        a.setSurname("Петров");

        assertEquals("Петров", a.getSurname());
    }

    @Test
    void failedSetSurnameKeepsOldValue() {
        Abonent a = new Abonent("Иванов", "Иван", List.of("111"));

        assertThrows(BusinessException.class, () -> a.setSurname(""));

        assertEquals("Иванов", a.getSurname());
    }

    @Test
    void getPhonesReturnsCopy() {
        Abonent a = new Abonent("Иванов", "Иван", List.of("111"));

        List<String> phones = a.getPhones();
        phones.add("999");

        assertEquals(List.of("111"), a.getPhones());
    }

    @Test
    void changingSourceListDoesNotAffectAbonent() {
        List<String> source = new ArrayList<>(List.of("111"));
        Abonent a = new Abonent("Иванов", "Иван", source);

        source.add("222");

        assertEquals(List.of("111"), a.getPhones());
    }

    @Test
    void getPhonesReturnsNewListEachTime() {
        Abonent a = new Abonent("Иванов", "Иван", List.of("111"));

        assertNotSame(a.getPhones(), a.getPhones());
    }

    @Test
    void setIdStoresId() {
        Abonent a = new Abonent("Иванов", "Иван", List.of("111"));

        a.setId(5);

        assertEquals(5, a.getId());
    }

    @Test
    void toStringContainsMainData() {
        Abonent a = new Abonent("Иванов", "Иван", List.of("111", "222"));
        a.setId(1);

        String text = a.toString();

        assertTrue(text.contains("Иванов"));
        assertTrue(text.contains("Иван"));
        assertTrue(text.contains("111, 222"));
        assertTrue(text.contains("[1]"));
    }
}