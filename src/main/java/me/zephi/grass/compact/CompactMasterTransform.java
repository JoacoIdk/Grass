package me.zephi.grass.compact;

import me.zephi.grass.ProtectedObject;
import me.zephi.grass.modifier.ByteModifier;
import me.zephi.grass.tag.DefaultTransform;
import me.zephi.grass.tag.ITypeTransform;
import me.zephi.grass.tag.MasterTransform;
import me.zephi.grass.tag.Tag;

import java.util.HashMap;
import java.util.Map;

public class CompactMasterTransform implements MasterTransform {
    private final Map<Integer, ITypeTransform<?>> transforms = new HashMap<>();
    private final ProtectedObject<DefaultTransform> defaultTransform = new ProtectedObject<>();

    @Override
    public void registerTransform(ITypeTransform<?> transform) {
        if (transform instanceof DefaultTransform)
            throw new IllegalArgumentException("A DefaultTransform must be registered with the setDefaultTransform(DefaultTransform) method.");

        transform.setMasterTransform(this);

        Class<?> type = transform.getType();

        String typePath = type.getName();
        int typeId = typePath.hashCode();

        transforms.put(typeId, transform);
    }

    @Override
    public void setDefaultTransform(DefaultTransform transform) {
        defaultTransform.set(transform);
        defaultTransform.lock();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tag<?> readTag(ByteModifier modifier) {
        if (!modifier.canModify(Integer.BYTES * 2 + 1))
            return null;

        String name = modifier.readString();
        int typeId = modifier.readInt();

        ITypeTransform<?> transform = transforms.get(typeId);

        if (transform == null)
            transform = defaultTransform.get();

        Class<Object> type = (Class<Object>) transform.getType();

        Object data = transform.readData(modifier);

        return new Tag<>(name, type, data);
    }

    @Override
    public void writeTag(ByteModifier modifier, Tag<?> tag) {
        if (tag == null)
            return;

        String name = tag.name();
        Class<?> type = tag.type();
        String typeName = type.getName();
        int typeId = typeName.hashCode();
        Object data = tag.data();

        ITypeTransform<?> transform = transforms.get(typeId);

        if (transform == null)
            transform = defaultTransform.get();

        modifier.writeString(name);
        modifier.writeInt(typeId);

        transform.writeUnsafeData(modifier, data);
    }
}
