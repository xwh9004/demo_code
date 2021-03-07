package com.example.aop;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * classsName is filePath like :com/demo/jvm/MyTest
 */
public class MyFooClassFileTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer)
            throws IllegalClassFormatException {
        if(!"com/demo/jvm/MyTestMain".equals(className)){
            return classfileBuffer;
        }
        ClassReader cr= new ClassReader(classfileBuffer);
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_FRAMES);
        ClassVisitor cv = new MyFooClassVisitor(cw);
        cr.accept(cv,ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG);
        return  cw.toByteArray();
    }
}
