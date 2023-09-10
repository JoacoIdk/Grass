package me.zephi.grass.conversions.compact.primitive;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.AbstractTypeTransform;

public class CompactLongTransform extends AbstractTypeTransform<Long> {
    @Override
    public Long readData(ByteModifier modifier) {
        return modifier.readLong();
    }

    @Override
    public void writeData(ByteModifier modifier, Long data) {
        if (data == null) {
            modifier.writeLong(0L);
            return;
        }

        modifier.writeLong(data);
    }

    @Override
    public Class<Long> getType() {
        return Long.class;
    }
}
