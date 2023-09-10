package me.zephi.grass.tag;

public class TransformException extends RuntimeException {
    public TransformException(String message) {
        super(message);
    }

    public TransformException(String message, Throwable parent) {
        super(message, parent);
    }
}
