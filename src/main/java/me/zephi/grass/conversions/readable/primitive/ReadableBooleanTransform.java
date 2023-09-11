package me.zephi.grass.conversions.readable.primitive;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.AbstractTypeTransform;

public class ReadableBooleanTransform extends AbstractTypeTransform<Boolean> {
    @Override
    public Boolean readData(ByteModifier modifier) {
        boolean value;

        StringBuilder buffer = new StringBuilder();
        char read;

        while (modifier.canRead(Byte.BYTES)) {
            read = modifier.readByteChar();

            if (read == '}')
                break;

            buffer.append(read);
        }

        value = Boolean.getBoolean(buffer.toString());

        return value;
    }

    @Override
    public void writeData(ByteModifier modifier, Boolean data) {
        if (data == null)
            data = false;

        modifier.writeBytesString(data.toString());
    }

    @Override
    public Class<Boolean> getType() {
        return Boolean.class;
    }
}
