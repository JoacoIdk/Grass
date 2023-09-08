package me.zephi.grass.modifier.input;

import me.zephi.grass.tag.Tag;

public interface InputModifier {
    Tag<?> readTag();
    void writeTag(Tag<?> tag);

    void clear();
    void close();
}
