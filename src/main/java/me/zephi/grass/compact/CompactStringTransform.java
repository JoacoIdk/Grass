package me.zephi.grass.compact;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.TypeTransform;

public class CompactStringTransform extends TypeTransform<String> {
    @Override
    public String readData(ByteModifier modifier) {
        return modifier.readString();
    }

    @Override
    public void writeData(ByteModifier modifier, String data) {
        modifier.writeString(data);
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}
