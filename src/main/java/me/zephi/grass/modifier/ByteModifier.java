package me.zephi.grass.modifier;

public interface ByteModifier {
    public boolean canModify(int size);
    public byte[] readBytes(int size);
    public void writeBytes(byte[] writingBytes);
    public String readBytesString(int size);
    public void writeBytesString(String writingBytes);
    public boolean readBoolean();
    public void writeBoolean(boolean value);
    public byte readByte();
    public void writeByte(byte value);
    public short readShort();
    public void writeShort(short value);
    public int readInt();
    public void writeInt(int value);
    public long readLong();
    public void writeLong(long value);
    public float readFloat();
    public void writeFloat(float value);
    public double readDouble();
    public void writeDouble(double value);
    public char readChar();
    public void writeChar(char value);
    public String readString();
    public void writeString(String value);
}
