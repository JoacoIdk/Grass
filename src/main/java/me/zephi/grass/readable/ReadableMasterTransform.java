package me.zephi.grass.readable;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.DefaultTransform;
import me.zephi.grass.tag.ITypeTransform;
import me.zephi.grass.tag.MasterTransform;
import me.zephi.grass.tag.Tag;

public class ReadableMasterTransform implements MasterTransform {
    @Override
    public void registerTransform(ITypeTransform<?> transform) {
    }

    @Override
    public void setDefaultTransform(DefaultTransform transform) {
    }

    @Override
    public Tag<?> readTag(ByteModifier modifier) {
        return null;
    }

    @Override
    public void writeTag(ByteModifier modifier, Tag<?> tag) {
    }
}
