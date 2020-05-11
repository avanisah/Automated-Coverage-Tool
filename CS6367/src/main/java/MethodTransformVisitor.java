package CS6367_Phase1;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


class MethodTransformVisitor extends MethodVisitor implements Opcodes {

	String methodName;
	int line;
	private String className;

	public MethodTransformVisitor(final MethodVisitor mv, String name, String className) {
		super(ASM5, mv);
		this.methodName = name;
		this.className = className;
	}


    @Override
    public void visitLabel(Label label) {
        if (line == 0) return;
        String temp = className;
        mv.visitLdcInsn(temp);
        mv.visitLdcInsn(line);
        mv.visitMethodInsn(INVOKESTATIC, "CS6367_Phase1/StatementCoverage", "testStatement", "(Ljava/lang/String;I)V", false);
        super.visitLabel(label);
    }

    @Override
    public void visitLineNumber(int linenumber, Label start) {
        this.line = linenumber;
        if (linenumber == 0) return;
        String temp = className;
        mv.visitLdcInsn(temp);
        mv.visitLdcInsn(linenumber);
        mv.visitMethodInsn(INVOKESTATIC, "CS6367_Phase1/StatementCoverage", "testStatement", "(Ljava/lang/String;I)V", false);
        super.visitLineNumber(linenumber, start);
    }

}