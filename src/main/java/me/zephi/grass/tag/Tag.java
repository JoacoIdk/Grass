package me.zephi.grass.tag;

public record Tag<T>(String name, Class<? extends T> type, Object data) {
    @SuppressWarnings("unchecked")
    public static<U> Tag<U> create(String name, U data) {
        return new Tag<>(name, (Class<? extends U>) data.getClass(), data);
    }

    public static Tag<?> empty(String name) {
        return new Tag<>(name, Object.class, null);
    }
}
