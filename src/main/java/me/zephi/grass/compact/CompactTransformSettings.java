package me.zephi.grass.compact;

import me.zephi.grass.tag.TransformSettings;

public class CompactTransformSettings {
    public static TransformSettings DEFAULT = transform -> {
        transform.registerTransform(new CompactIntTransform());
        transform.registerTransform(new CompactStringTransform());

        transform.setDefaultTransform(new CompactDefaultTransform());
    };
}
