package me.zephi.grass.conversions.compact.primitive;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.AbstractTypeTransform;

public class CompactCharacterTransform extends AbstractTypeTransform<Character> {
    @Override
    public Character readData(ByteModifier modifier) {
        return modifier.readChar();
    }

    @Override
    public void writeData(ByteModifier modifier, Character data) {
        if (data == null) {
            modifier.writeChar((char) 0);
            return;
        }

        modifier.writeChar(data);
    }

    @Override
    public Class<Character> getType() {
        return Character.class;
    }
}
