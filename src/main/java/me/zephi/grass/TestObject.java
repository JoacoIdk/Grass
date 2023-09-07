package me.zephi.grass;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TestObject {
    private int a;
    private int b;

    TestObject(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int a() {
        return a;
    }

    public int b() {
        return b;
    }

    public void a(int a) {
        this.a = a;
    }

    public void b(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "TestObject[a=%d, b=%d]".formatted(a, b);
    }
}
