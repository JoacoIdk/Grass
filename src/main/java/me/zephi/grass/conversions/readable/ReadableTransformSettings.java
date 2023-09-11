package me.zephi.grass.conversions.readable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.zephi.grass.conversions.readable.primitive.*;
import me.zephi.grass.tag.TransformSettings;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ReadableTransformSettings {
    public static TransformSettings<ReadableMasterTransform> DEFAULT = transform -> {
        transform.registerTransform(new ReadableBooleanTransform());
        transform.registerTransform(new ReadableByteTransform());
        transform.registerTransform(new ReadableCharacterTransform());
        transform.registerTransform(new ReadableShortTransform());
        transform.registerTransform(new ReadableIntegerTransform());
        transform.registerTransform(new ReadableFloatTransform());
        transform.registerTransform(new ReadableLongTransform());
        transform.registerTransform(new ReadableDoubleTransform());
    };
}
