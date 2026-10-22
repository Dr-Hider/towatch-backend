package com.example.towatch.api.item.exception;

public class ItemAlreadyExistsException extends RuntimeException {
    public ItemAlreadyExistsException(String title) {
        super("Item already exists with title: " + title);
    }
}
