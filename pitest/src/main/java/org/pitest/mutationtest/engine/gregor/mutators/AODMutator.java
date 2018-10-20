package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.mutationtest.engine.gregor.AbstractInsnMutator;
import org.pitest.mutationtest.engine.gregor.ZeroOperandMutation;
import org.pitest.mutationtest.engine.gregor.InsnSubstitution;

public enum AODMutator implements MethodMutatorFactory {

    AOD_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context,
                                final MethodInfo methodInfo,
                                final MethodVisitor methodVisitor) {
        return new AODMethodVisitor(this, methodInfo, context, methodVisitor);
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

class AODMethodVisitor extends AbstractInsnMutator {

    private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<>();

    AODMethodVisitor(final MethodMutatorFactory factory,
                     final MethodInfo methodInfo,
                     final MutationContext context,
                     final MethodVisitor writer) {
        super(factory, methodInfo, context, writer);
    }


    static final String INT_MESSAGE = "AOD, INT: Removed the operator";

    static {

        //Pops two ints from the operand stack, adds them, and pushes the integer result back onto the stack
        MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.POP, INT_MESSAGE));

        //Pops two ints from the operand stack, subs them, and pushes the integer result back onto the stack
        MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.POP, INT_MESSAGE));

        //Pops the top two integers from the operand stack, multiplies them, and pushes the integer result back onto the stack.
        MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.POP, INT_MESSAGE));

        // Pops the top two integers from the operand stack, divides them, and pushes the integer result back onto the stack.
        MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.POP, INT_MESSAGE));
    }

    @Override
    protected Map<Integer, ZeroOperandMutation> getMutations() {
        return MUTATIONS;
    }

}