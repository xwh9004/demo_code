package com.demo.io.dma;

public class CasDemo {

    public static void main(String[] args) throws InterruptedException {
        twoThreadPersonFlushCacheLine(false);
        //时间最长
        twoThreadPersonFlushCacheLine(true);
        twoThreadChildrenFlushCacheLine(false);
        twoThreadChildrenFlushCacheLine(true);

    }

    private static void twoThreadPersonFlushCacheLine(boolean overwrite) throws InterruptedException {
        Student student = new Student();
        Student student2 = new Student();
        student.setAge(42);
        Thread thread = new Thread() {
            @Override
            public void run() {
                //read 1000000000 times;
                long count = 0;
                long max = 1000000000;
                long start = System.currentTimeMillis();
                while (count++ < max) {
                    long age = student.getAge();
                    student2.setAge(age);
                }
                long end = System.currentTimeMillis();
                System.out.println("change student  耗时 " + (end - start) + "ms");
            }
        };


        Thread thread1 = new Thread() {
            @Override
            public void run() {
                //read 1000000000 times;
                long count = 0;
                long max = 1000000000;
                while (count++ < max) {
                    if (overwrite) {
                        student.setId(count);
                    }
                }
            }
        };
        thread.start();
        thread1.start();

        thread.join();
        thread1.join();
    }

    private static void twoThreadChildrenFlushCacheLine(boolean overwrite) throws InterruptedException {
        Children children = new Children();
        Children children2 = new Children();
        children.setAge(42);
        Thread thread = new Thread() {
            @Override
            public void run() {
                //read 1000000000 times;
                long count = 0;
                long max = 1000000000;
                long start = System.currentTimeMillis();
                while (count++ < max) {
                    long age = children.getAge();
                    children2.setAge(age);
                }
                long end = System.currentTimeMillis();
                System.out.println("change children  耗时 " + (end - start) + "ms");
            }
        };


        Thread thread1 = new Thread() {
            @Override
            public void run() {
                //read 1000000000 times;
                long count = 0;
                long max = 1000000000;
                while (count++ < max) {
                    if (overwrite) {
                        children.setId(count);
                    }
                }
            }
        };
        thread.start();
        thread1.start();

        thread.join();
        thread1.join();
    }
}
