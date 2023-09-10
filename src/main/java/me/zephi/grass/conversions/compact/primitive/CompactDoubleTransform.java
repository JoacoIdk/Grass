package me.zephi.grass.conversions.compact.primitive;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.AbstractTypeTransform;

public class CompactDoubleTransform extends AbstractTypeTransform<Double> {
    @Override
    public Double readData(ByteModifier modifier) {
        return modifier.readDouble();
    }

    @Override
    public void writeData(ByteModifier modifier, Double data) {
        if (data == null) {
            modifier.writeDouble(0D);
            return;
        }

        modifier.writeDouble(data);
    }

    @Override
    public Class<Double> getType() {
        return Double.class;
    }
}
