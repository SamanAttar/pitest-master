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
    static {
        MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.ISUB, MessageInt + "addition with subtraction"));
        MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IADD,  MessageInt + "subtraction with addition"));
        MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IDIV, MessageInt +  "multiplication with division"));
        MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IMUL, MessageInt + "division with multiplication"));
    }

    @Override
    protected Map<Integer, ZeroOperandMutation> getMutations() {
        return MUTATIONS;
    }

}