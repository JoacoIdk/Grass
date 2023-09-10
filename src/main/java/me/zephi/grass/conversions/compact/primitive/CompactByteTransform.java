package me.zephi.grass.conversions.compact.primitive;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.AbstractTypeTransform;

public class CompactByteTransform extends AbstractTypeTransform<Byte> {
    @Override
    public Byte readData(ByteModifier modifier) {
        return modifier.readByte();
    }

    @Override
    public void writeData(ByteModifier modifier, Byte data) {
        if (data == null) {
            modifier.writeByte((byte) 0);
            return;
        }

        modifier.writeByte(data);
    }

    @Override
    public Class<Byte> getType() {
        return Byte.class;
    }
}
