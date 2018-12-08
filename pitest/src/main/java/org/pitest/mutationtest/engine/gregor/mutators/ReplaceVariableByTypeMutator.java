package org.pitest.mutationtest.engine.gregor.mutators;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public enum ReplaceVariableByTypeMutator implements MethodMutatorFactory {
    REPLACE_VARIABLE_BY_TYPE_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context,
                                final MethodInfo methodInfo,
                                final MethodVisitor methodVisitor) {
        return new ReplaceVariableByTypeMutatorVisitor(this, context, methodVisitor);
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

class ReplaceVariableByTypeMutatorVisitor extends MethodVisitor {
    private final MethodMutatorFactory factory;
    private final MutationContext context;
    private List<Integer> mutationVarIndex = new ArrayList<>();
    private List<String> dataTypeList = new ArrayList<>();

    ReplaceVariableByTypeMutatorVisitor(final MethodMutatorFactory factory,
                                        final MutationContext context,
                                        final MethodVisitor delegateMethodVisitor) {
        super(Opcodes.ASM6, delegateMethodVisitor);
        this.factory = factory;
        this.context = context;
    }

    @Override
    public void visitVarInsn(final int opcode, final int var) {

        HashMap<Integer, String> opcodeStoreMap = new HashMap<>();
        opcodeStoreMap.put(Opcodes.ISTORE, "Integer");
        opcodeStoreMap.put(Opcodes.LSTORE, "Long");
        opcodeStoreMap.put(Opcodes.FSTORE, "Float");
        opcodeStoreMap.put(Opcodes.DSTORE, "Double");
        opcodeStoreMap.put(Opcodes.ASTORE, "Object");

        HashMap<Integer, String> opcodeLoadMap = new HashMap<>();
        opcodeLoadMap.put(Opcodes.ILOAD, "Integer");
        opcodeLoadMap.put(Opcodes.LLOAD, "Long");
        opcodeLoadMap.put(Opcodes.FLOAD, "Float");
        opcodeLoadMap.put(Opcodes.DLOAD, "Double");
        opcodeLoadMap.put(Opcodes.ALOAD, "Object");


        /* check if opcode is of type STORE */
        if(opcodeStoreMap.containsKey(opcode)){
            //if this var has not been added to the index
            if (!mutationVarIndex.contains(var)) {
                mutationVarIndex.add(var);
                dataTypeList.add(opcodeStoreMap.get(opcode)); //adds the data type i.e. Integer, Double etc... (not the opcode value)
            }
        }

        /* check if opcode is of type LOAD */
        else if(opcodeLoadMap.containsKey(opcode)) {
            if (mutationVarIndex.contains(var)) {
                int index = mutationVarIndex.indexOf(var);

                Random random = new Random();
                int replacementIndex = random.nextInt(mutationVarIndex.size());

                int count = 0;
                for( String dataType : dataTypeList){
                    if(dataType.equals(dataTypeList.get(index)))
                        count++;
                    if(count > 1)
                    {
                        String newValue = dataTypeList.get(replacementIndex);
                        String currentValue = dataTypeList.get(index);
                        while (!currentValue.equals(newValue) || index == replacementIndex) {
                            //create a new replacement index
                            replacementIndex = random.nextInt(mutationVarIndex.size());
                            newValue = dataTypeList.get(replacementIndex);
                        }
                        break;
                    }
                }

                //create the new mutation
                String currentDataType = dataTypeList.get(index);
                String description = currentDataType + ": value changed.";
                final MutationIdentifier newId = this.context.registerMutation(this.factory,  description);

                if (this.context.shouldMutate(newId)) {
                    int newVar = mutationVarIndex.get(replacementIndex);
                    super.visitVarInsn(opcode, newVar);
                } else {
                    super.visitVarInsn(opcode, var);
                }
            }
            else {
                super.visitVarInsn(opcode, var);
            }
        }
        else {
            super.visitVarInsn(opcode, var);
        }
    }
}
