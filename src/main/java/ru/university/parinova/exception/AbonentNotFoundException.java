package ru.university.parinova.exception;

public class AbonentNotFoundException extends BusinessException {
    public AbonentNotFoundException(int id) {
        super("Абонент с id = " + id + " не найден");
    }
}