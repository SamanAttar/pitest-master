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


public enum AORMutator implements MethodMutatorFactory {

    AOR_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context,
                                final MethodInfo methodInfo,
                                final MethodVisitor methodVisitor) {
        return new AORMethodVisitor(this, methodInfo, context, methodVisitor);
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

class AORMethodVisitor extends AbstractInsnMutator {

    private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<>();

    AORMethodVisitor(final MethodMutatorFactory factory,
                     final MethodInfo methodInfo,
                     final MutationContext context,
                     final MethodVisitor writer) {
        super(factory, methodInfo, context, writer);
    }

    static final String MESSAGE_INT = "AOR Replace Integer";
    static final String MESSAGE_FLOAT = "AOR Replace Float";
    static final String MESSAGE_LONG = "AOR Replace Long";
    static final String MESSAGE_DOUBLE = "AOR Replace Double";

    static final String REPLACE_ADD_WITH_SUB = "addition with subtraction";
    static final String REPLACE_SUB_WITH_ADD = "subtraction with addition";
    static final String REPLACE_MULT_WITH_DIV = "multiplication with division";
    static final String REPLACE_DIV_WITH_MULT = "division with multiplication";

    static {
        MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.ISUB,MESSAGE_INT + REPLACE_ADD_WITH_SUB));
        MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IADD,MESSAGE_INT + REPLACE_SUB_WITH_ADD));
        MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IDIV,MESSAGE_INT +  REPLACE_MULT_WITH_DIV));
        MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IMUL,MESSAGE_INT + REPLACE_DIV_WITH_MULT));

        MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FSUB,MESSAGE_FLOAT + REPLACE_ADD_WITH_SUB));
        MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FADD,MESSAGE_FLOAT + REPLACE_SUB_WITH_ADD));
        MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FDIV,MESSAGE_FLOAT + REPLACE_MULT_WITH_DIV));
        MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FMUL,MESSAGE_FLOAT + REPLACE_DIV_WITH_MULT));

        MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LSUB,MESSAGE_LONG + REPLACE_ADD_WITH_SUB));
        MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LADD,MESSAGE_LONG + REPLACE_SUB_WITH_ADD));
        MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LDIV,MESSAGE_LONG + REPLACE_MULT_WITH_DIV));
        MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LMUL,MESSAGE_LONG + REPLACE_DIV_WITH_MULT));

        MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DSUB,MESSAGE_DOUBLE + REPLACE_ADD_WITH_SUB));
        MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DADD,MESSAGE_DOUBLE + REPLACE_SUB_WITH_ADD));
        MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DDIV,MESSAGE_DOUBLE + REPLACE_MULT_WITH_DIV));
        MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DMUL,MESSAGE_DOUBLE + REPLACE_DIV_WITH_MULT));
    }

    @Override
    protected Map<Integer, ZeroOperandMutation> getMutations() {
        return MUTATIONS;
    }
}