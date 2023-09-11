package me.zephi.grass.conversions.readable.primitive;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.AbstractTypeTransform;

public class ReadableByteTransform extends AbstractTypeTransform<Byte> {
    @Override
    public Byte readData(ByteModifier modifier) {
        modifier.readByteChar();

        byte value;

        StringBuilder buffer = new StringBuilder();
        char read;

        while (modifier.canRead(Byte.BYTES)) {
            read = modifier.readByteChar();

            if (read == '\n')
                break;

            buffer.append(read);
        }

        value = Byte.parseByte(buffer.toString());

        return value;
    }

    @Override
    public void writeData(ByteModifier modifier, Byte data) {
        modifier.writeByteChar(' ');

        if (data == null)
            data = 0;

        modifier.writeBytesString(data.toString());
        modifier.writeByteChar('\n');
    }

    @Override
    public Class<Byte> getType() {
        return Byte.class;
    }
}
