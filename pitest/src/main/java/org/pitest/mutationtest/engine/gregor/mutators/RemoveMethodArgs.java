package org.pitest.mutationtest.engine.gregor.mutators;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import static org.objectweb.asm.Type.getArgumentTypes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum RemoveMethodArgs implements MethodMutatorFactory {

    REMOVE_METHOD_ARGS;

    @Override
    public MethodVisitor create(final MutationContext context,
                                final MethodInfo methodInfo,
                                final MethodVisitor methodVisitor) {
        return new RemoveMethodArgsVisitor(context, methodVisitor, this);
    }

    @Override
    public String getGloballyUniqueId() {
        return this.getClass().getName();
    }

    @Override
    public String getName() {
        return name();
    }

}

class RemoveMethodArgsVisitor extends MethodVisitor {

    private final MethodMutatorFactory factory;
    private final MutationContext   context;

    public RemoveMethodArgsVisitor(final MutationContext context,
                                   final MethodVisitor writer,
                                   final MethodMutatorFactory factory) {
        super(Opcodes.ASM6, writer);
        this.factory = factory;
        this.context = context;
    }

    @Override
    public void visitMethodInsn(final int opcode, final String methodName, final String methodParameters, final String description, final boolean isInterface) {

        //check for more than 2 parameters
        // desc example -> DILjava/lang/String;
        if (getArgumentTypes(description).length > 2) {
            final MutationIdentifier newId = this.context.registerMutation(this.factory, "Removed parameters from: " + methodName + " method.");
            Type[] methodParameterArray = getArgumentTypes(description);
            int arrayLength = methodParameterArray.length;

            // Check for a POP on the first parameter of the method
            if (methodParameterArray[arrayLength- 1].getSize() == 1)
                mv.visitInsn(Opcodes.POP);
            else
                mv.visitInsn(Opcodes.POP2);

            // Check for a POP on the second parameter of the method
            if (methodParameterArray[arrayLength - 2].getSize() == 1) {
                mv.visitInsn(Opcodes.POP);
            } else {
                mv.visitInsn(Opcodes.POP2);
            }

            Type[] parameterReplacementArray = new Type[arrayLength];

            int count = 0;
            for(Type type : methodParameterArray){
                parameterReplacementArray[count] = type;
                count++;
            }

            String newMethodParameters = Type.getMethodDescriptor(Type.getReturnType(description), parameterReplacementArray);
            this.mv.visitMethodInsn(opcode, methodName, newMethodParameters, description, isInterface);
        }
        else {
            this.mv.visitMethodInsn(opcode, methodName, description, description, isInterface);
        }
    }

}
