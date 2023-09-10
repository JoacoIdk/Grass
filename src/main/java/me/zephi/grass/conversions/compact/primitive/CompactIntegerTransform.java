package me.zephi.grass.conversions.compact.primitive;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.AbstractTypeTransform;

public class CompactIntegerTransform extends AbstractTypeTransform<Integer> {
    @Override
    public Integer readData(ByteModifier modifier) {
        return modifier.readInt();
    }

    @Override
    public void writeData(ByteModifier modifier, Integer data) {
        if (data == null) {
            modifier.writeInt(0);
            return;
        }

        modifier.writeInt(data);
    }

    @Override
    public Class<Integer> getType() {
        return Integer.class;
    }
}
