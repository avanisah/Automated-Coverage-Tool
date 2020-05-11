package CS6367_Phase1;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class MyClassVisitor extends ClassVisitor implements Opcodes {

    private String className;

    public MyClassVisitor(final ClassVisitor classVisitor, final String className) {
        super(ASM5, classVisitor);
        this.className = className;
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        return mv == null ? null : new MethodTransformVisitor(mv, name, className);
    }
 
}