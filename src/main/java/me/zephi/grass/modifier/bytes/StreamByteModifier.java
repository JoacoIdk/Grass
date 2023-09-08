package me.zephi.grass.modifier.bytes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// TODO: Complete class
public class StreamByteModifier implements ByteModifier {
    private final InputStream input;
    private final OutputStream output;

    public StreamByteModifier(InputStream input, OutputStream output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public boolean canModify(int size) {
        try {
            return input.available() > size;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] readBytes(int size) {
        try {
            return input.readNBytes(size);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeBytes(byte[] writingBytes) {
        try {
            for (int index = 0; index < writingBytes.length; index++)
                output.write(writingBytes[index]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readBytesString(int size) {
        return new String(readBytes(size));
    }

    @Override
    public void writeBytesString(String writingBytes) {
        writeBytes(writingBytes.getBytes());
    }

    @Override
    public boolean readBoolean() {
        try {
            return input.read() == 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeBoolean(boolean value) {
        try {
            output.write(value ? 1 : 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte readByte() {
        try {
            return (byte) input.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeByte(byte value) {
        try {
            output.write(value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public short readShort() {
        try {
            short value = 0;

            for (int index = 0; index < Short.BYTES; index++)
                value = (short) (value | (input.read() & 0xFF) << (Byte.SIZE * index));

            return value;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeShort(short value) {
        try {
            for (int index = 0; index < Short.BYTES; index++)
                output.write((byte) (value >>> (Byte.SIZE * index)) & 0xFF);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int readInt() {
        try {
            int value = 0;

            for (int index = 0; index < Integer.BYTES; index++)
                value = value | (input.read() & 0xFF) << (Byte.SIZE * index);

            return value;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeInt(int value) {
        try {
            for (int index = 0; index < Integer.BYTES; index++)
                output.write((value >>> (Byte.SIZE * index)) & 0xFF);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long readLong() {
        try {
            long value = 0;

            for (int index = 0; index < Long.BYTES; index++)
                value = value | (input.read() & 0xFFL) << (Byte.SIZE * index);

            return value;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeLong(long value) {
        try {
            for (int index = 0; index < Long.BYTES; index++)
                output.write((int) ((value >>> (Byte.SIZE * index)) & 0xFFL));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public float readFloat() {
        try {
            int bits = 0;

            for (int index = 0; index < Integer.BYTES; index++)
                bits = bits | (input.read() & 0xFF) << (Byte.SIZE * index);

            return Float.intBitsToFloat(bits);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeFloat(float value) {
        try {
            int bits = Float.floatToIntBits(value);

            for (int index = 0; index < Integer.BYTES; index++)
                output.write((byte) (bits >>> (Byte.SIZE * index)) & 0xFF);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double readDouble() {
        try {
            long bits = 0;

            for (int index = 0; index < Long.BYTES; index++)
                bits = bits | (input.read() & 0xFFL) << (Byte.SIZE * index);

            return Double.longBitsToDouble(bits);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeDouble(double value) {
        try {
            long bits = Double.doubleToLongBits(value);

            for (int index = 0; index < Long.BYTES; index++)
                output.write((int) ((bits >>> (Byte.SIZE * index)) & 0xFFL));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public char readChar() {
        try {
            char value = 0;

            for (int index = 0; index < Character.BYTES; index++)
                value = (char) (value | (input.read() & 0xFF) << (Byte.SIZE * index));

            return value;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeChar(char value) {
        try {
            for (int index = 0; index < Character.BYTES; index++)
                output.write((byte) (value >>> (Byte.SIZE * index)) & 0xFF);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readString() {
        try {
            int stringSize = 0;

            for (int index = 0; index < Integer.BYTES; index++)
                stringSize = stringSize | (input.read() & 0xFF) << (Byte.SIZE * index);

            byte[] stringBytes = new byte[stringSize];

            for (int index = 0; index < stringSize; index++)
                stringBytes[index] = (byte) input.read();

            return new String(stringBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeString(String value) {
        try {
            byte[] stringBytes = value.getBytes();
            int stringSize = stringBytes.length;

            for (int index = 0; index < Integer.BYTES; index++)
                output.write((byte) (stringSize >>> (Byte.SIZE * index)) & 0xFF);

            for (int index = 0; index < stringSize; index++)
                output.write(stringBytes[index]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
