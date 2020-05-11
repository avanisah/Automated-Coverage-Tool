package CS6367_Phase1;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ClassReader;


public class MyClassFileTransformer implements ClassFileTransformer {

    private final String packageName;

    public MyClassFileTransformer(String packageName) {
        this.packageName = packageName;
    }

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if (className.startsWith(packageName)){
            ClassReader class_reader = new ClassReader(classfileBuffer);
            ClassWriter class_writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            MyClassVisitor classvisitor = new MyClassVisitor(class_writer, className);
            class_reader.accept(classvisitor, 0);
            return class_writer.toByteArray();
        }
        return classfileBuffer;
    }
}
