package com.example.aop;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class AgentMain {

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new MyClassFileTransformer(),true);
    }
    //
    public static void agentmain(String agentArgs, Instrumentation inst) throws UnmodifiableClassException {
        System.out.println("agentmain called");
        inst.addTransformer(new MyFooClassFileTransformer(),true);
        Class classes[] = inst.getAllLoadedClasses();
        for (int i=0;i<classes.length;i++){
            System.out.println(classes[i].getName());
            //这里类名是 包名形式
            if(classes[i].getName().equals("com.demo.jvm.MyTestMain")){
                System.out.printf("Reloading:"+ classes[i].getName());
                inst.retransformClasses(classes[i]);
                break;
            }
        }
    }
}
