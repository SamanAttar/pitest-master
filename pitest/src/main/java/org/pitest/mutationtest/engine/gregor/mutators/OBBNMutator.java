package org.pitest.mutationtest.engine.gregor.mutators;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractInsnMutator;
import org.pitest.mutationtest.engine.gregor.*;

import java.util.HashMap;
import java.util.Map;

public enum OBBNMutator implements MethodMutatorFactory {

    OBBN_MUTATOR;

    public MethodVisitor create(final MutationContext context,
                                final MethodInfo methodInfo,
                                final MethodVisitor methodVisitor) {
        return new OBBNMethodVisitor(this, methodInfo, context, methodVisitor);
    }

    public String getGloballyUniqueId() {
        return this.getClass().getName();
    }

    public String getName() {
        return name();
    }

}

class OBBNMethodVisitor extends AbstractInsnMutator {

    OBBNMethodVisitor(final MethodMutatorFactory factory,
                       final MethodInfo methodInfo,
                       final MutationContext context,
                       final MethodVisitor writer) {
        super(factory, methodInfo, context, writer);
    }

    private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<>();

    static {
        // Integer Replacement
        MUTATIONS.put(Opcodes.IAND, new InsnSubstitution(Opcodes.IOR, "OBBN Mutator, int Replaced & with |"));
        MUTATIONS.put(Opcodes.IOR, new InsnSubstitution(Opcodes.IAND, "OBBN Mutator, int. Replaced | with &"));

        // Long Replacement
        MUTATIONS.put(Opcodes.LAND, new InsnSubstitution(Opcodes.LOR, "OBBN Mutator,long. Replaced & with |"));
        MUTATIONS.put(Opcodes.LOR, new InsnSubstitution(Opcodes.LAND, "OBBN Mutator,long. Replaced | with &"));
    }

    @Override
    protected Map<Integer, ZeroOperandMutation> getMutations() {
        return MUTATIONS;
    }
}

