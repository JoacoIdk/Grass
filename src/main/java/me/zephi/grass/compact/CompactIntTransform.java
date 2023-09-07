package me.zephi.grass.compact;

import me.zephi.grass.modifier.ByteModifier;
import me.zephi.grass.tag.TypeTransform;

public class CompactIntTransform extends TypeTransform<Integer> {
    @Override
    public Integer readData(ByteModifier modifier) {
        return modifier.readInt();
    }

    @Override
    public void writeData(ByteModifier modifier, Integer data) {
        modifier.writeInt(data);
    }

    @Override
    public Class<Integer> getType() {
        return Integer.class;
    }
}