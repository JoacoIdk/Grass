package me.zephi.grass.conversions.compact;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.zephi.grass.conversions.compact.extras.CompactStringTransform;
import me.zephi.grass.conversions.compact.primitive.*;
import me.zephi.grass.tag.TransformSettings;

@NoArgsConstructor(access = AccessLevel.NONE)
public class CompactTransformSettings {
    public static TransformSettings<CompactMasterTransform> DEFAULT = transform -> {
        transform.registerTransform(new CompactBooleanTransform());
        transform.registerTransform(new CompactByteTransform());
        transform.registerTransform(new CompactCharacterTransform());
        transform.registerTransform(new CompactShortTransform());
        transform.registerTransform(new CompactIntegerTransform());
        transform.registerTransform(new CompactFloatTransform());
        transform.registerTransform(new CompactLongTransform());
        transform.registerTransform(new CompactDoubleTransform());

        transform.registerTransform(new CompactStringTransform());

        transform.setDefaultTransform(new CompactDefaultTransform());
    };
}
