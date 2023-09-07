package me.zephi.grass.tag;

import me.zephi.grass.modifier.ByteModifier;

public interface ITypeTransform<T> {
    void setMasterTransform(MasterTransform transform);

    T readData(ByteModifier modifier);
    void writeData(ByteModifier modifier, T data);
    Class<T> getType();

    default void writeUnsafeData(ByteModifier modifier, Object data) {
        writeData(modifier, (T) data);
    }
}