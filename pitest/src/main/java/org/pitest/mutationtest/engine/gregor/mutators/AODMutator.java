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

    static final String INT_MESSAGE = "AOD, INT: Removed the first operator";
    static final String DOUBLE_MESSAGE = "AOD, Double: Removed the first operator";
    static final String LONG_MESSAGE = "AOD, Long: Removed the first operator";
    static final String FLOAT_MESSAGE = "AOD, Float: Removed the first operator";

    static {

        /** Ints **/
        //Pops two ints from the operand stack, adds them, and pushes the integer result back onto the stack
        MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.POP, INT_MESSAGE));

        //Pops two ints from the operand stack, subs them, and pushes the integer result back onto the stack
        MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.POP, INT_MESSAGE));

        //Pops the top two integers from the operand stack, multiplies them, and pushes the integer result back onto the stack.
        MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.POP, INT_MESSAGE));

        // Pops the top two integers from the operand stack, divides them, and pushes the integer result back onto the stack.
        MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.POP, INT_MESSAGE));


        /** Doubles **/
        //Pops two doubles from the operand stack, adds them, and pushes the integer result back onto the stack
        MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.POP2, DOUBLE_MESSAGE));

        //Pops two subs from the operand stack, subs them, and pushes the doubles result back onto the stack
        MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.POP2, DOUBLE_MESSAGE));

        //Pops the top doubles integers from the operand stack, multiplies them, and pushes the doubles result back onto the stack.
        MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.POP2, DOUBLE_MESSAGE));

        //Pops the top two doubles from the operand stack, divides them, and pushes the double result back onto the stack.
        MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.POP2, DOUBLE_MESSAGE));

        /** Longs **/
        //Pops two longs from the operand stack, adds them, and pushes the longs result back onto the stack
        MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.POP2, LONG_MESSAGE));

        //Pops two longs from the operand stack, subs them, and pushes the longs result back onto the stack
        MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.POP2, LONG_MESSAGE));

        //Pops two longs from the operand stack, multiplies them, and pushes the longs result back onto the stack
        MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.POP2, LONG_MESSAGE));

        //Pops two longs from the operand stack, divides them, and pushes the longs result back onto the stack
        MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.POP2, LONG_MESSAGE));

        /** Floats **/
        //Pops two floats from the operand stack, adds them, and pushes the floats result back onto the stack
        MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.POP, FLOAT_MESSAGE));

        //Pops two floats from the operand stack, subs them, and pushes the floats result back onto the stack
        MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.POP, FLOAT_MESSAGE));

        //Pops two floats from the operand stack, mults them, and pushes the floats result back onto the stack
        MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.POP, FLOAT_MESSAGE));

        //Pops two floats from the operand stack, divs them, and pushes the floats result back onto the stack
        MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.POP, FLOAT_MESSAGE));
    }

    @Override
    protected Map<Integer, ZeroOperandMutation> getMutations() {
        return MUTATIONS;
    }

}