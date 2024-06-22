package com.nixiedroid.rest;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class Preloader implements ClassFileTransformer {
    static String classPrefix = Preloader.class.getPackageName().replace(".", "/");

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new Preloader());
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new Preloader());
    }

    @Override
    public byte[] transform(Module module, ClassLoader loader,
                            String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        if (className.startsWith(classPrefix)) {
            System.out.println("Loaded [" + className + "] from module: " + module.getName());
        }
        return null;
    }
}
