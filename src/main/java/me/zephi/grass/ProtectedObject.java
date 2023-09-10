package me.zephi.grass;

import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
public class ProtectedObject<T> {
    private T value = null;
    private boolean locked = false;

    public void lock() {
        this.locked = true;
    }

    public void set(T value) {
        if (locked)
            return;

        this.value = value;
    }

    public boolean present() {
        return value != null;
    }

    public T get(T obj) {
        return value == null ? obj : value;
    }

    public T get() {
        return value;
    }

    @Override
    public String toString() {
        return "ProtectedObject[value=%s, lock=%s]".formatted(value.toString(), locked);
    }
}
