package me.zephi.grass;

import me.zephi.grass.compact.CompactMasterTransform;
import me.zephi.grass.compact.CompactTransformSettings;
import me.zephi.grass.tag.MasterTransform;
import me.zephi.grass.tag.TransformSettings;

/**
 * This class contains methods to quickly create basic transforms, however
 * you should consider creating transforms with their MasterTransform class
 * applying the settings you need manually since this class could be modified
 * drastically in a future update.
 */
public class Grass {
    public static MasterTransform createCompactTransform(TransformSettings settings) {
        return settings.get(new CompactMasterTransform());
    }

    public static MasterTransform createCompactTransform() {
        return createCompactTransform(CompactTransformSettings.DEFAULT);
    }
}