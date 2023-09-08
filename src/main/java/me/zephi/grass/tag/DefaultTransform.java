package me.zephi.grass.tag;

import me.zephi.grass.modifier.bytes.ByteModifier;

public interface DefaultTransform extends ITypeTransform<Object> {
    @Override
    default void setMasterTransform(MasterTransform transform) {}

    @Override
    Object readData(ByteModifier modifier);

    @Override
    void writeData(ByteModifier modifier, Object data);

    @Override
    default Class<Object> getType() {
        return Object.class;
    }
}
