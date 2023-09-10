package me.zephi.grass.conversions.compact.primitive;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.AbstractTypeTransform;

public class CompactFloatTransform extends AbstractTypeTransform<Float> {
    @Override
    public Float readData(ByteModifier modifier) {
        return modifier.readFloat();
    }

    @Override
    public void writeData(ByteModifier modifier, Float data) {
        if (data == null) {
            modifier.writeFloat(0F);
            return;
        }

        modifier.writeFloat(data);
    }

    @Override
    public Class<Float> getType() {
        return Float.class;
    }
}
