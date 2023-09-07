package me.zephi.grass.tag;

public record Tag<T>(String name, Class<T> type, T data) {}
