package me.zephi.grass.conversions.readable.primitive;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.AbstractTypeTransform;

public class ReadableIntegerTransform extends AbstractTypeTransform<Integer> {
    @Override
    public Integer readData(ByteModifier modifier) {
        modifier.readByteChar();

        int value;

        StringBuilder buffer = new StringBuilder();
        char read;

        while (modifier.canRead(Byte.BYTES)) {
            read = modifier.readByteChar();

            if (read == '\n')
                break;

            buffer.append(read);
        }

        value = Integer.parseInt(buffer.toString());

        return value;
    }

    @Override
    public void writeData(ByteModifier modifier, Integer data) {
        modifier.writeByteChar(' ');

        if (data == null)
            data = 0;

        modifier.writeBytesString(data.toString());
        modifier.writeByteChar('\n');
    }

    @Override
    public Class<Integer> getType() {
        return Integer.class;
    }
}
