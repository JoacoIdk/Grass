package me.zephi.grass.conversions.readable.primitive;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.AbstractTypeTransform;

public class ReadableDoubleTransform extends AbstractTypeTransform<Double> {
    @Override
    public Double readData(ByteModifier modifier) {
        modifier.readByteChar();

        double value;

        StringBuilder buffer = new StringBuilder();
        char read;

        while (modifier.canRead(Byte.BYTES)) {
            read = modifier.readByteChar();

            if (read == '\n')
                break;

            buffer.append(read);
        }

        value = Double.parseDouble(buffer.toString());

        return value;
    }

    @Override
    public void writeData(ByteModifier modifier, Double data) {
        modifier.writeByteChar(' ');

        if (data == null)
            data = 0D;

        modifier.writeBytesString(data.toString());
        modifier.writeByteChar('\n');
    }

    @Override
    public Class<Double> getType() {
        return Double.class;
    }
}
