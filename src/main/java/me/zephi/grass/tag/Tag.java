package me.zephi.grass.tag;

public record Tag<T>(String name, Class<? extends T> type, Object data) {
    public static<U> Tag<?> create(String name, U data) {
        return new Tag<>(name, data.getClass(), data);
    }

    public static Tag<?> empty(String name) {
        return new Tag<>(name, Object.class, null);
    }
}
