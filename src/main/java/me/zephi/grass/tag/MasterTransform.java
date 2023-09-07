package me.zephi.grass.tag;

import me.zephi.grass.modifier.ByteModifier;

public interface MasterTransform {
    void registerTransform(ITypeTransform<?> transform);
    void setDefaultTransform(DefaultTransform transform);

    Tag<?> readTag(ByteModifier modifier);
    void writeTag(ByteModifier modifier, Tag<?> tag);
}
