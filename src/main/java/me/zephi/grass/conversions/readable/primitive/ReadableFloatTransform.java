package me.zephi.grass.conversions.readable.primitive;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.AbstractTypeTransform;

public class ReadableFloatTransform extends AbstractTypeTransform<Float> {
    @Override
    public Float readData(ByteModifier modifier) {
        modifier.readByteChar();

        float value;

        StringBuilder buffer = new StringBuilder();
        char read;

        while (modifier.canRead(Byte.BYTES)) {
            read = modifier.readByteChar();

            if (read == '\n')
                break;

            buffer.append(read);
        }

        value = Float.parseFloat(buffer.toString());

        return value;
    }

    @Override
    public void writeData(ByteModifier modifier, Float data) {
        modifier.writeByteChar(' ');

        if (data == null)
            data = 0F;

        modifier.writeBytesString(data.toString());
        modifier.writeByteChar('\n');
    }

    @Override
    public Class<Float> getType() {
        return Float.class;
    }
}
