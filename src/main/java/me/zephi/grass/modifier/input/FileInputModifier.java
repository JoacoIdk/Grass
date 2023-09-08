package me.zephi.grass.modifier.input;

import me.zephi.grass.modifier.bytes.ByteModifier;
import me.zephi.grass.modifier.bytes.StreamByteModifier;
import me.zephi.grass.tag.MasterTransform;
import me.zephi.grass.tag.Tag;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileInputModifier implements InputModifier {
    private final MasterTransform transform;
    private final Path path;

    private InputStream input;
    private OutputStream output;

    private ByteModifier modifier;

    public FileInputModifier(MasterTransform transform, Path path) {
        this.transform = transform;
        this.path = path;

        try {
            this.input = Files.newInputStream(path);
            this.output = Files.newOutputStream(path, StandardOpenOption.APPEND, StandardOpenOption.WRITE);
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
    public void clear() {
        try {
            input.close();
            output.close();

            Files.deleteIfExists(path);
            Files.createFile(path);

            input = Files.newInputStream(path);
            output = Files.newOutputStream(path, StandardOpenOption.APPEND, StandardOpenOption.WRITE);

            modifier = new StreamByteModifier(input, output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
