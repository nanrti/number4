package ru.university.parinova.exception;

public class AbonentSurnameException extends BusinessException {
    public AbonentSurnameException() {
        super("Фамилия не может быть пустой");
    }
}