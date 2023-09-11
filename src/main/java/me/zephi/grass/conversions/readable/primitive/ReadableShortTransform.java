package me.zephi.grass.conversions.readable.primitive;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.AbstractTypeTransform;

public class ReadableShortTransform extends AbstractTypeTransform<Short> {
    @Override
    public Short readData(ByteModifier modifier) {
        short value;

        StringBuilder buffer = new StringBuilder();
        char read;

        while (modifier.canRead(Byte.BYTES)) {
            read = modifier.readByteChar();

            if (read == '}')
                break;

            buffer.append(read);
        }

        value = Short.parseShort(buffer.toString());

        return value;
    }

    @Override
    public void writeData(ByteModifier modifier, Short data) {
        if (data == null)
            data = 0;

        modifier.writeBytesString(data.toString());
    }

    @Override
    public Class<Short> getType() {
        return Short.class;
    }
}
