package me.zephi.grass.conversions.readable;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.DefaultTransform;

public class ReadableDefaultTransform extends DefaultTransform {
    @Override
    public Object readData(ByteModifier modifier) {
        return null;
    }

    @Override
    public void writeData(ByteModifier modifier, Object data) {}
}
