package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.*;

public enum AORMutator implements MethodMutatorFactory {

    AOR_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context,
                                final MethodInfo methodInfo,
                                final MethodVisitor methodVisitor)
    {
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
                     final MethodVisitor writer)
    {
        super(factory, methodInfo, context, writer);
    }

    static final String MessageInt = "AOR, Replace Integer ";
    static final String MessageFloat = "AOR, Replace Float ";
    static final String MessageLong = "AOR, Replace Long ";
    static final String MessageDouble = "AOR, Replace Double ";

    static final String replaceAddWithSub = "addition with subtraction";
    static final String replaceSubWithAdd = "subtraction with addition";
    static final String replaceMultWithDiv = "multiplication with division";
    static final String replaceDivWithMult = "division with multiplication";

    static {
        MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.ISUB,MessageInt + replaceAddWithSub));
        MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IADD,MessageInt + replaceSubWithAdd));
        MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IDIV,MessageInt +  replaceMultWithDiv));
        MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IMUL,MessageInt + replaceDivWithMult));

        MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FSUB,MessageFloat + replaceAddWithSub));
        MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FADD,MessageFloat + replaceSubWithAdd));
        MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FDIV,MessageFloat + replaceMultWithDiv));
        MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FMUL,MessageFloat + replaceDivWithMult));

        MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LSUB,MessageLong + replaceAddWithSub));
        MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LADD,MessageLong + replaceSubWithAdd));
        MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LDIV,MessageLong + replaceMultWithDiv));
        MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LMUL,MessageLong + replaceDivWithMult));

        MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DSUB,MessageDouble + replaceAddWithSub));
        MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DADD,MessageDouble + replaceSubWithAdd));
        MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DDIV,MessageDouble + replaceMultWithDiv));
        MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DMUL,MessageDouble + replaceDivWithMult));
    }

    @Override
    protected Map<Integer, ZeroOperandMutation> getMutations() {
        return MUTATIONS;
    }

}