package com.github.jengo.threaddp.test.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * https://www.cnblogs.com/mickole/articles/3757278.html
 * https://tech.meituan.com/2019/02/14/talk-about-java-magic-class-unsafe.html
 *
 * Unsafe提供的API大致可分为：
 * 1.内存操作：主要包含"堆外内存"的分配、拷贝、释放、给定地址值操作等方法，典型应用 DirectByteBuffer。
 * 2.CAS：Unsafe提供的CAS方法（如compareAndSwapXXX）底层实现即为CPU指令cmpxchg。
 * 3.Class相关：主要提供Class和它的静态字段的操作相关方法，包含静态字段内存定位、定义类、定义匿名类、检验&确保初始化等。
 * 4.对象操作：主要包含对象成员属性相关操作及非常规的对象实例化方式等相关方法。
 * 5.线程调度：包括线程挂起、恢复、锁机制等方法。典型应用，Java锁和同步器框架的核心类AbstractQueuedSynchronizer，就是通过调用LockSupport.park()和LockSupport.unpark()实现线程的阻塞和唤醒的，而LockSupport的park、unpark方法实际是调用Unsafe的park、unpark方式来实现。
 * 6.系统信息获取：包含两个获取系统相关信息的方法。
 * 7.内存屏障
 * 8.数组操作
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
            UNSAFE = reflectGetUnsafe();
            Class<?> k = Test.class;
            //对象操作
            aOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("a"));
            bOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("b"));
            cOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("c"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public static void main(String[] args) throws Exception {
        Test test = new Test();

        System.out.println(String.format("aOffset = %s, a = %s", Test.aOffset, test.getA()));
        System.out.println(String.format("bOffset = %s, b = %s", Test.bOffset, test.getB()));
        System.out.println(String.format("cOffset = %s, c = %s", Test.cOffset, test.getC()));
        System.out.println();

        Test.UNSAFE.compareAndSwapInt(test, Test.aOffset, 1, test.getA() * 10);
        Test.UNSAFE.compareAndSwapInt(test, Test.bOffset, 2, test.getB() * 10);
        Test.UNSAFE.compareAndSwapInt(test, Test.cOffset, 3, test.getC() * 10);

        System.out.println(String.format("aOffset = %s, a = %s", Test.aOffset, test.getA()));
        System.out.println(String.format("bOffset = %s, b = %s", Test.bOffset, test.getB()));
        System.out.println(String.format("cOffset = %s, c = %s", Test.cOffset, test.getC()));
        System.out.println();

        //系统相关
        System.out.println(String.format("系统指针大小 = %s", UNSAFE.addressSize()));
        System.out.println(String.format("系统内存页大小 = %s", UNSAFE.pageSize()));
    }

    private static Unsafe reflectGetUnsafe() {
        try {
            // 获取Unsafe内部的私有的实例化单利对象
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            // 无视权限
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
