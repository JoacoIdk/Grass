package me.zephi.grass.conversions.readable.primitive;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.AbstractTypeTransform;

public class ReadableLongTransform extends AbstractTypeTransform<Long> {
    @Override
    public Long readData(ByteModifier modifier) {
        modifier.readByteChar();

        long value;

        StringBuilder buffer = new StringBuilder();
        char read;

        while (modifier.canRead(Byte.BYTES)) {
            read = modifier.readByteChar();

            if (read == '\n')
                break;

            buffer.append(read);
        }

        value = Long.parseLong(buffer.toString());

        return value;
    }

    @Override
    public void writeData(ByteModifier modifier, Long data) {
        modifier.writeByteChar(' ');

        if (data == null)
            data = 0L;

        modifier.writeBytesString(data.toString());
        modifier.writeByteChar('\n');
    }

    @Override
    public Class<Long> getType() {
        return Long.class;
    }
}
