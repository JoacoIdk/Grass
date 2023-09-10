package me.zephi.grass.conversions.compact.extras;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.AbstractTypeTransform;

public class CompactStringTransform extends AbstractTypeTransform<String> {
    @Override
    public String readData(ByteModifier modifier) {
        return modifier.readString();
    }

    @Override
    public void writeData(ByteModifier modifier, String data) {
        if (data == null) {
            modifier.writeInt(0);
            return;
        }

        modifier.writeString(data);
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}
