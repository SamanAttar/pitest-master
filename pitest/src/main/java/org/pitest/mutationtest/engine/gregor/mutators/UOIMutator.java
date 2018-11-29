package org.pitest.mutationtest.engine.gregor.mutators;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum UOIMutator implements MethodMutatorFactory {

    UOI_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context,
                                final MethodInfo methodInfo,
                                final MethodVisitor methodVisitor) {
        return new UOIMethodVisitor(this, context, methodVisitor);
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

class UOIMethodVisitor extends MethodVisitor {

    private final MethodMutatorFactory factory;
    private final MutationContext context;

    UOIMethodVisitor(final MethodMutatorFactory factory,
                     final MutationContext context,
                     final MethodVisitor delegateMethodVisitor) {
        super(Opcodes.ASM6, delegateMethodVisitor);
        this.factory = factory;
        this.context = context;
    }

    @Override
    public void visitVarInsn(final int opcode, final int var) {

        // found instance of storing integer in local variable
        if (opcode == Opcodes.ISTORE) {
            //create a new instance of the MutationIdentifier for the negation
            final MutationIdentifier newId = this.context.registerMutation(this.factory,  "UOI MUTATOR. int ++.");
            if (this.context.shouldMutate(newId)) {
                //push the integer constant 1, to simulate i++
                super.visitInsn(Opcodes.ICONST_1);
                // add the two ints in the stack
                super.visitInsn(Opcodes.IADD);
            }

        }
        // found instance of storing double in local variable
        else if (opcode == Opcodes.DSTORE) {
            //create a new instance of the MutationIdentifier for the negation
            final MutationIdentifier newId = this.context.registerMutation(this.factory, "UOI MUTATOR. double ++.");
            if (this.context.shouldMutate(newId)) {
                //push the double constant 1
                super.visitInsn(Opcodes.DCONST_1);
                // add the two doubles in the stack
                super.visitInsn(Opcodes.DADD);
            }
        }

        // found instance of storing long in local variable
        else if (opcode == Opcodes.LSTORE) {
            //create a new instance of the MutationIdentifier for the negation
            final MutationIdentifier newId = this.context.registerMutation(this.factory,  "UOI MUTATOR. long ++.");
            if (this.context.shouldMutate(newId)) {
                //push the long constant 1
                super.visitInsn(Opcodes.LCONST_1);
                // add the two longs in the stack
                super.visitInsn(Opcodes.LADD);
            }
        }
        // found instance of storing float in local variable
        else if (opcode == Opcodes.FSTORE) {
            final MutationIdentifier newId = this.context.registerMutation(this.factory, "UOI MUTATOR. float ++.");
            if (this.context.shouldMutate(newId)) {
                //push the float constant 1
                super.visitInsn(Opcodes.FCONST_1);
                // add the two floats in the stack
                super.visitInsn(Opcodes.FADD);
            }
        }
        super.visitVarInsn(opcode, var);
    }

    @Override
    public void visitIincInsn(final int var, final int val) {
        //create a new instance of the MutationIdentifier for the negation
        final MutationIdentifier newId = this.context.registerMutation(this.factory, "UOI MUTATOR. i++ and i-- ");

        if (this.context.shouldMutate(newId)) {
            this.mv.visitIincInsn(var, 0);
        } else {
            this.mv.visitIincInsn(var, 0);
        }
    }

}