package com.learning.aop;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class MyClassVisitor extends ClassVisitor {


    public MyClassVisitor(ClassVisitor classVisitor) {
       super(Opcodes.ASM5,classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if(name.equals("<init>")){
            return mv;
        }
        return new MyMethodVisitor(mv,access,name,desc);
    }
}
