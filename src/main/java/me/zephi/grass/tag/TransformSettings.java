package me.zephi.grass.tag;

public interface TransformSettings {
    void apply(MasterTransform transform);

    default MasterTransform get(MasterTransform transform) {
        apply(transform);

        return transform;
    }
}
