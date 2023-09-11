package me.zephi.grass;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TestObject {
    private int a;
    private int b;
    private int c;
    private int d;

    TestObject(int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public int a() {
        return a;
    }

    public int b() {
        return b;
    }

    public int c() {
        return c;
    }

    public int d() {
        return d;
    }

    public void a(int a) {
        this.a = a;
    }

    public void b(int b) {
        this.b = b;
    }

    public void c(int c) {
        this.c = c;
    }

    public void d(int d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "TestObject[a=%d, b=%d, c=%d, d=%d]".formatted(a, b, c, d);
    }
}
