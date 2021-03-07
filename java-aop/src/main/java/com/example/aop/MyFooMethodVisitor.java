package com.example.aop;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

public class MyFooMethodVisitor extends AdviceAdapter {
    private MethodVisitor mv ;
    private String name;

    protected MyFooMethodVisitor(MethodVisitor mv, int access, String name, String desc) {
        super(ASM5, mv, access, name, desc);
        this.mv = mv;
        this.name = name;
    }

    @Override
    protected void onMethodEnter() {
       mv.visitIntInsn(BIPUSH,50);
       mv.visitInsn(IRETURN);
    }
}
