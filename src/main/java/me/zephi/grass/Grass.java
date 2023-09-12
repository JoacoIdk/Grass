package me.zephi.grass;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.zephi.grass.conversions.compact.CompactMasterTransform;
import me.zephi.grass.conversions.compact.CompactTransformSettings;
import me.zephi.grass.conversions.readable.ReadableMasterTransform;
import me.zephi.grass.conversions.readable.ReadableTransformSettings;
import me.zephi.grass.modifier.input.CacheInputModifier;
import me.zephi.grass.modifier.input.FileInputModifier;
import me.zephi.grass.modifier.input.InputModifier;
import me.zephi.grass.tag.MasterTransform;
import me.zephi.grass.tag.TransformSettings;

import java.nio.file.Path;

/**
 * This class contains methods to quickly create basic transforms, however
 * you should consider creating transforms with their MasterTransform class
 * applying the settings you need manually since this class could be modified
 * drastically in a future update.
 */
@NoArgsConstructor(access = AccessLevel.NONE)
public class Grass {

    // Transforms

    public static MasterTransform createCompactTransform(TransformSettings<CompactMasterTransform> settings) {
        return settings.get(new CompactMasterTransform());
    }

    public static MasterTransform createCompactTransform() {
        return createCompactTransform(CompactTransformSettings.DEFAULT);
    }

    public static MasterTransform createReadableTransform(TransformSettings<ReadableMasterTransform> settings) {
        return settings.get(new ReadableMasterTransform());
    }

    public static MasterTransform createReadableTransform() {
        return createReadableTransform(ReadableTransformSettings.DEFAULT);
    }

    // Input modifiers

    public static InputModifier createFileInputModifier(MasterTransform transform, Path path) {
        return new FileInputModifier(transform, path);
    }

    public static InputModifier createCacheInputModifier(InputModifier parent) {
        return new CacheInputModifier(parent);
    }

    public static InputModifier createCacheFileInputModifier(MasterTransform transform, Path path) {
        return new CacheInputModifier(new FileInputModifier(transform, path));
    }
}