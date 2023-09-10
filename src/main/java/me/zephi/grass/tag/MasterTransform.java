package me.zephi.grass.tag;

import me.zephi.grass.modifier.bytes.ByteModifier;

public interface MasterTransform {
    void registerTransform(TypeTransform<?> transform);
    void setDefaultTransform(DefaultTransform transform);

    Tag<?> readTag(ByteModifier modifier);
    void writeTag(ByteModifier modifier, Tag<?> tag);
}
