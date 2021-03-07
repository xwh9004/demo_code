package com.example.aop;


import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

public class MyMethodVisitor extends AdviceAdapter {
    private MethodVisitor mv ;
    private String name;

    protected MyMethodVisitor(MethodVisitor mv, int access, String name, String desc) {
        super(ASM5, mv, access, name, desc);
        this.mv = mv;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    @Override
    protected void onMethodEnter() {
        //在方法开始处插入<<<enter xxx
        mv.visitFieldInsn(GETSTATIC,"java/lang/System","out","Ljava/io/PrintStream;");
        mv.visitLdcInsn("<<<enter " + this.getName());
        mv.visitMethodInsn(INVOKEVIRTUAL,"java/io/PrintStream","println","(Ljava/lang/String;)V",false);
        super.onMethodEnter();
    }

    @Override
    protected void onMethodExit(int opcode) {
        super.onMethodExit(opcode);
        //在方法结束处插入>>> exit xxx
        mv.visitFieldInsn(GETSTATIC,"java/lang/System","out","Ljava/io/PrintStream;");
        mv.visitLdcInsn("<<<exit " + this.getName());
        mv.visitMethodInsn(INVOKEVIRTUAL,"java/io/PrintStream","println","(Ljava/lang/String;)V",false);
    }
}
