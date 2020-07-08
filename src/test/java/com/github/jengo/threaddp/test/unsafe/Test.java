package com.github.jengo.threaddp.test.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * https://www.cnblogs.com/mickole/articles/3757278.html
 * https://tech.meituan.com/2019/02/14/talk-about-java-magic-class-unsafe.html
 */
public class Test {

    private int a = 1;
    private int b = 2;
    private int c = 3;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    // Unsafe mechanics
    private static final sun.misc.Unsafe UNSAFE;
    private static final long aOffset;
    private static final long bOffset;
    private static final long cOffset;

    static {
        try {
            // 获取Unsafe内部的私有的实例化单利对象
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            // 无视权限
            field.setAccessible(true);

            UNSAFE = (Unsafe) field.get(null);
            Class<?> k = Test.class;
            aOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("a"));
            bOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("b"));
            cOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("c"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public static void main(String[] args) throws Exception {
        Test test = new Test();

        System.out.println("aOffset = " + Test.aOffset + ", a = " + test.getA());
        System.out.println("bOffset = " + Test.bOffset + ", b = " + test.getB());
        System.out.println("cOffset = " + Test.cOffset + ", c = " + test.getC());
        System.out.println();

        Test.UNSAFE.compareAndSwapInt(test, Test.aOffset, 1, test.getA() * 10);
        Test.UNSAFE.compareAndSwapInt(test, Test.bOffset, 2, test.getB() * 10);
        Test.UNSAFE.compareAndSwapInt(test, Test.cOffset, 3, test.getC() * 10);

        System.out.println("aOffset = " + Test.aOffset + ", a = " + test.getA());
        System.out.println("bOffset = " + Test.bOffset + ", b = " + test.getB());
        System.out.println("cOffset = " + Test.cOffset + ", c = " + test.getC());
        System.out.println();
    }

}
