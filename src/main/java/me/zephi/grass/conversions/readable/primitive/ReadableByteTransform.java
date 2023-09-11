package me.zephi.grass.conversions.readable.primitive;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.AbstractTypeTransform;

public class ReadableByteTransform extends AbstractTypeTransform<Byte> {
    @Override
    public Byte readData(ByteModifier modifier) {
        byte value;

        StringBuilder buffer = new StringBuilder();
        char read;

        while (modifier.canRead(Byte.BYTES)) {
            read = modifier.readByteChar();

            if (read == '}')
                break;

            buffer.append(read);
        }

        value = Byte.parseByte(buffer.toString());

        return value;
    }

    @Override
    public void writeData(ByteModifier modifier, Byte data) {
        if (data == null)
            data = 0;

        modifier.writeBytesString(data.toString());
    }

    @Override
    public Class<Byte> getType() {
        return Byte.class;
    }
}
