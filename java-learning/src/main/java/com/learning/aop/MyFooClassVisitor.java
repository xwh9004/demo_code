package com.learning.aop;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class MyFooClassVisitor extends ClassVisitor {


    public MyFooClassVisitor(ClassVisitor classVisitor) {
       super(Opcodes.ASM5,classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if(name.equals("foo")){
            return new MyFooMethodVisitor(mv,access,name,desc);
        }
        return mv;
    }
}
