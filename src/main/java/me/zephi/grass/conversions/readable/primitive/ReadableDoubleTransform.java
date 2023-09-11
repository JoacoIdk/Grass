package me.zephi.grass.conversions.readable.primitive;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.AbstractTypeTransform;

public class ReadableDoubleTransform extends AbstractTypeTransform<Double> {
    @Override
    public Double readData(ByteModifier modifier) {
        double value;

        StringBuilder buffer = new StringBuilder();
        char read;

        while (modifier.canRead(Byte.BYTES)) {
            read = modifier.readByteChar();

            if (read == '}')
                break;

            buffer.append(read);
        }

        value = Double.parseDouble(buffer.toString());

        return value;
    }

    @Override
    public void writeData(ByteModifier modifier, Double data) {
        if (data == null)
            data = 0D;

        modifier.writeBytesString(data.toString());
    }

    @Override
    public Class<Double> getType() {
        return Double.class;
    }
}
