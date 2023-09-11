package me.zephi.grass.conversions.readable.extras;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.AbstractTypeTransform;

public class ReadableStringTransform extends AbstractTypeTransform<String> {
    @Override
    public String readData(ByteModifier modifier) {
        String value;

        StringBuilder buffer = new StringBuilder();
        char prev;
        char read = 0;
        boolean allow = false;

        while (modifier.canRead(Byte.BYTES)) {
            prev = read;
            read = modifier.readByteChar();

            if (read == '"') {
                allow = prev != '"';

                if (!allow)
                    continue;
            }

            if (read == '}' && allow)
                break;

            buffer.append(read);
        }

        value = buffer.substring(1, buffer.length() - 1);

        return value;
    }

    @Override
    public void writeData(ByteModifier modifier, String data) {
        if (data == null)
            data = "";

        modifier.writeBytesString('"' + data.replace("\"", "\"\"") + '"');
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}
