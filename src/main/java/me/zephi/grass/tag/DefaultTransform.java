package me.zephi.grass.tag;

public abstract class DefaultTransform extends AbstractTypeTransform<Object> {
    @Override
    public Class<Object> getType() {
        return Object.class;
    }
}
