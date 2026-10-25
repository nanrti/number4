package ru.university.parinova.exception;

public class AbonentNameException extends BusinessException {
    public AbonentNameException() {
        super("Имя не может быть пустым");
    }
}