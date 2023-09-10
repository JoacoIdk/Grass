package me.zephi.grass.tag;

public interface TransformSettings<T extends MasterTransform> {
    void apply(T transform);

    default T get(T transform) {
        apply(transform);

        return transform;
    }
}
