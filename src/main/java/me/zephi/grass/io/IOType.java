package me.zephi.grass.io;

import me.zephi.grass.tag.Tag;

public interface IOType {
    Tag<?> readTag();
    void writeTag(Tag<?> tag);

    void close();
}
