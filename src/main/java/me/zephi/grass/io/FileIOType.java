package me.zephi.grass.io;

import me.zephi.grass.modifier.ByteModifier;
import me.zephi.grass.modifier.StreamByteModifier;
import me.zephi.grass.tag.MasterTransform;
import me.zephi.grass.tag.Tag;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

// TODO: Make class actually read from file instead of caching file contents to an ArrayByteModifier (change to StreamByteModifier)
public class FileIOType implements IOType {
    private final MasterTransform transform;
    private final Path path;

    private final InputStream input;
    private final OutputStream output;

    private final ByteModifier modifier;

    public FileIOType(MasterTransform transform, Path path) {
        this.transform = transform;
        this.path = path;

        try {
            if (!Files.exists(path))
                Files.createFile(path);

            this.input = Files.newInputStream(path, StandardOpenOption.READ, StandardOpenOption.CREATE);
            this.output = Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND, StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.modifier = new StreamByteModifier(input, output);
    }

    @Override
    public Tag<?> readTag() {
        return transform.readTag(modifier);
    }

    @Override
    public void writeTag(Tag<?> tag) {
        transform.writeTag(modifier, tag);
    }

    @Override
    public void close() {
        try {
            input.close();
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
