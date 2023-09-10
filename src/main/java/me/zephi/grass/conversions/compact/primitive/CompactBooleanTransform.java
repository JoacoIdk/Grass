package me.zephi.grass.conversions.compact.primitive;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.tag.AbstractTypeTransform;

public class CompactBooleanTransform extends AbstractTypeTransform<Boolean> {
    @Override
    public Boolean readData(ByteModifier modifier) {
        return modifier.readBoolean();
    }

    @Override
    public void writeData(ByteModifier modifier, Boolean data) {
        if (data == null) {
            modifier.writeBoolean(false);
            return;
        }

        modifier.writeBoolean(data);
    }

    @Override
    public Class<Boolean> getType() {
        return Boolean.class;
    }
}
