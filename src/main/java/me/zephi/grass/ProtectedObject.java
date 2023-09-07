package me.zephi.grass;

import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
public class ProtectedObject<T> {
    private Optional<T> value = Optional.empty();
    private boolean locked = false;

    public void lock() {
        this.locked = true;
    }

    public void set(T value) {
        if (locked)
            return;

        this.value = Optional.of(value);
    }

    public boolean present() {
        return value.isPresent();
    }

    public T get(T obj) {
        return value.orElse(obj);
    }

    public T get() {
        return value.get();
    }

    @Override
    public String toString() {
        return "ProtectedObject[value=%s, lock=%s]".formatted(value.toString(), locked);
    }
}
