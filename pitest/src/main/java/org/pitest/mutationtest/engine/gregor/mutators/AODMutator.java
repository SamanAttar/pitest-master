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


    private static final String MESSAGE ="";

    static {

    }

    @Override
    protected Map<Integer, ZeroOperandMutation> getMutations() {
        return MUTATIONS;
    }

}