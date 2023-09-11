package me.zephi.grass.conversions.readable.primitive;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.AbstractTypeTransform;

public class ReadableCharacterTransform extends AbstractTypeTransform<Character> {
    @Override
    public Character readData(ByteModifier modifier) {
        char value;

        StringBuilder buffer = new StringBuilder();
        char read;

        while (modifier.canRead(Byte.BYTES)) {
            read = modifier.readByteChar();

            if (read == '}')
                break;

            buffer.append(read);
        }

        value = buffer.isEmpty() ? 0 : buffer.charAt(0);

        return value;
    }

    @Override
    public void writeData(ByteModifier modifier, Character data) {
        if (data == null)
            data = 0;

        modifier.writeBytesString(data.toString());
    }

    @Override
    public Class<Character> getType() {
        return Character.class;
    }
}
