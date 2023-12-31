package me.zephi.grass.modifier.input;

import me.zephi.grass.tag.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CacheInputModifier implements InputModifier {
    private final InputModifier parent;
    private final List<Tag<?>> tags;
    private int readCursor = 0;
    private int writeCursor = 0;

    public CacheInputModifier(InputModifier parent) {
        this.parent = parent;
        this.tags = new ArrayList<>();
        Tag<?> tag = parent.readTag();

        while (tag != null) {
            tags.add(tag);
            writeCursor++;

            tag = parent.readTag();
        }
    }

    public void removeFirst(Predicate<? super Tag<?>> predicate) {
        Tag<?> tag = queryFirst(predicate);

        if (tag != null)
            tags.remove(tag);
    }

    public void removeAll(Predicate<? super Tag<?>> predicate) {
        tags.removeIf(predicate);
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
        if (writeCursor >= tags.size())
            tags.add(tag);
        else
            tags.set(writeCursor, tag);

        writeCursor++;
    }

    @Override
    public void clear() {
        tags.clear();
    }

    @Override
    public void close() {
        parent.clear();

        for (Tag<?> tag : tags)
            parent.writeTag(tag);

        parent.close();
    }
}