package me.zephi.grass.tag;

public abstract class AbstractTypeTransform<T> implements TypeTransform<T> {
    private MasterTransform transform = null;

    @Override
    public void setMasterTransform(MasterTransform transform) {
        this.transform = transform;
    }

    public MasterTransform transform() {
        return transform;
    }
}
