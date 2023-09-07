package me.zephi.grass.io;

import me.zephi.grass.tag.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CacheIOType implements IOType {
    private final IOType parent;
    private final List<Tag<?>> tags;
    private int readCursor = 0;
    private int writeCursor = 0;

    public CacheIOType(IOType parent) {
        this.parent = parent;
        this.tags = new ArrayList<>();
        Tag<?> tag = parent.readTag();

        while (tag != null) {
            tags.add(tag);
            tag = parent.readTag();
        }
    }

    public Tag<?> queryFirst(Predicate<? super Tag<?>> predicate) {
        return tags.stream().filter(predicate).findFirst().orElse(null);
    }

    public List<Tag<?>> queryAll(Predicate<? super Tag<?>> predicate) {
        return tags.stream().filter(predicate).toList();
    }

    public Tag<?> queryFirstName(String name) {
        return queryFirst((tag) -> tag.name().equals(name));
    }

    public List<Tag<?>> queryAllName(String name) {
        return queryAll((tag) -> tag.name().equals(name));
    }

    public Tag<?> queryFirstType(Class<?> type) {
        return queryFirst((tag) -> tag.type().equals(type));
    }

    public List<Tag<?>> queryAllType(Class<?> type) {
        return queryAll((tag) -> tag.type().equals(type));
    }

    public void readCursor(int cursor) {
        this.readCursor = cursor;
    }

    public void writeCursor(int cursor) {
        this.writeCursor = cursor;
    }

    @Override
    public Tag<?> readTag() {
        return tags.get(readCursor++);
    }

    @Override
    public void writeTag(Tag<?> tag) {
        if (tags.size() <= writeCursor)
            tags.add(tag);
        else
            tags.set(writeCursor, tag);

        writeCursor++;
    }

    @Override
    public void close() {
        for (Tag<?> tag : tags)
            parent.writeTag(tag);

        parent.close();
    }
}