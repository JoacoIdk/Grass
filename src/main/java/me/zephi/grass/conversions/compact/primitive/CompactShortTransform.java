package me.zephi.grass.conversions.compact.primitive;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.AbstractTypeTransform;

public class CompactShortTransform extends AbstractTypeTransform<Short> {
    @Override
    public Short readData(ByteModifier modifier) {
        return modifier.readShort();
    }

    @Override
    public void writeData(ByteModifier modifier, Short data) {
        if (data == null) {
            modifier.writeShort((short) 0);
            return;
        }

        modifier.writeShort(data);
    }

    @Override
    public Class<Short> getType() {
        return Short.class;
    }
}
