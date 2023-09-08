package me.zephi.grass.modifier.bytes;

public class ArrayByteModifier implements ByteModifier {
    private byte[] bytes;
    private int writeCursor;
    private int readCursor;

    public ArrayByteModifier() {
        bytes = new byte[0];
        readCursor = 0;
        writeCursor = 0;
    }

    public ArrayByteModifier(byte[] bytes) {
        this.bytes = bytes;
        readCursor = 0;
        writeCursor = bytes.length - 1;
    }

    public ArrayByteModifier(int size) {
        bytes = new byte[size];
        readCursor = 0;
        writeCursor = 0;
    }

    public boolean canModify(int size) {
        return writeCursor + size < bytes.length && readCursor + size < bytes.length;
    }

    public void adjustWriteSize(int size) {
        if (canModify(size))
            return;

        byte[] temp = bytes;
        bytes = new byte[writeCursor + size + 1];

        System.arraycopy(temp, 0, bytes, 0, temp.length);
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public byte[] readBytes(int size) {
        byte[] readingBytes = new byte[size];

        for (int index = 0; index < size; index++)
            readingBytes[index] = bytes[readCursor++];

        return readingBytes;
    }

    @Override
    public void writeBytes(byte[] writingBytes) {
        adjustWriteSize(writingBytes.length);

        for (int index = 0; index < writingBytes.length; index++)
            bytes[writeCursor++] = writingBytes[index];
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
        return bytes[readCursor++] == 1;
    }

    @Override
    public void writeBoolean(boolean value) {
        adjustWriteSize(Byte.BYTES);

        bytes[writeCursor++] = (byte) (value ? 1 : 0);
    }

    @Override
    public byte readByte() {
        return bytes[readCursor++];
    }

    @Override
    public void writeByte(byte value) {
        adjustWriteSize(Byte.BYTES);

        bytes[writeCursor++] = value;
    }

    @Override
    public short readShort() {
        short value = 0;

        for (int index = 0; index < Short.BYTES; index++)
            value = (short) (value | (bytes[readCursor++] & 0xFF) << (Byte.SIZE * index));

        return value;
    }

    @Override
    public void writeShort(short value) {
        adjustWriteSize(Short.BYTES);

        for (int index = 0; index < Short.BYTES; index++)
            bytes[writeCursor++] = (byte) ((value >>> (Byte.SIZE * index)) & 0xFF);
    }

    @Override
    public int readInt() {
        int value = 0;

        for (int index = 0; index < Integer.BYTES; index++)
            value = value | (bytes[readCursor++] & 0xFF) << (Byte.SIZE * index);

        return value;
    }

    @Override
    public void writeInt(int value) {
        adjustWriteSize(Integer.BYTES);

        for (int index = 0; index < Integer.BYTES; index++)
            bytes[writeCursor++] = (byte) ((value >>> (Byte.SIZE * index)) & 0xFF);
    }

    @Override
    public long readLong() {
        long value = 0;

        for (int index = 0; index < Long.BYTES; index++)
            value = value | (bytes[readCursor++] & 0xFFL) << (Byte.SIZE * index);

        return value;
    }

    @Override
    public void writeLong(long value) {
        adjustWriteSize(Long.BYTES);

        for (int index = 0; index < Long.BYTES; index++)
            bytes[writeCursor++] = (byte) ((value >>> (Byte.SIZE * index)) & 0xFFL);
    }

    @Override
    public float readFloat() {
        int bits = 0;

        for (int index = 0; index < Integer.BYTES; index++)
            bits = bits | (bytes[readCursor++] & 0xFF) << (Byte.SIZE * index);

        return Float.intBitsToFloat(bits);
    }

    @Override
    public void writeFloat(float value) {
        adjustWriteSize(Float.BYTES);

        int bits = Float.floatToIntBits(value);

        for (int index = 0; index < Integer.BYTES; index++)
            bytes[writeCursor++] = (byte) ((bits >>> (Byte.SIZE * index)) & 0xFF);
    }

    @Override
    public double readDouble() {
        long bits = 0;

        for (int index = 0; index < Long.BYTES; index++)
            bits = bits | (bytes[readCursor++] & 0xFFL) << (Byte.SIZE * index);

        return Double.longBitsToDouble(bits);
    }

    @Override
    public void writeDouble(double value) {
        adjustWriteSize(Double.BYTES);

        long bits = Double.doubleToLongBits(value);

        for (int index = 0; index < Long.BYTES; index++)
            bytes[writeCursor++] = (byte) ((bits >>> (Byte.SIZE * index)) & 0xFFL);
    }

    @Override
    public char readChar() {
        char value = 0;

        for (int index = 0; index < Character.BYTES; index++)
            value = (char) (value | (bytes[readCursor++] & 0xFF) << (Byte.SIZE * index));

        return value;
    }

    @Override
    public void writeChar(char value) {
        adjustWriteSize(Character.BYTES);

        for (int index = 0; index < Character.BYTES; index++)
            bytes[writeCursor++] = (byte) ((value >>> (Byte.SIZE * index)) & 0xFF);
    }

    @Override
    public String readString() {
        int stringSize = 0;

        for (int index = 0; index < Integer.BYTES; index++)
            stringSize = stringSize | (bytes[readCursor++] & 0xFF) << (Byte.SIZE * index);

        byte[] stringBytes = new byte[stringSize];

        for (int index = 0; index < stringSize; index++)
            stringBytes[index] = bytes[readCursor++];

        return new String(stringBytes);
    }

    @Override
    public void writeString(String value) {
        byte[] stringBytes = value.getBytes();
        int stringSize = stringBytes.length;

        adjustWriteSize(stringSize + Integer.BYTES);

        for (int index = 0; index < Integer.BYTES; index++)
            bytes[writeCursor++] = (byte) ((stringSize >>> (Byte.SIZE * index)) & 0xFF);

        for (int index = 0; index < stringSize; index++)
            bytes[writeCursor++] = stringBytes[index];
    }
}
