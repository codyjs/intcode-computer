package org.aoc.intcode;

public class Instruction {
	private Type type;
	private InstructionParameter[] params;
	
	public static Instruction fromInstructionPointer(int instructionPtr, IntcodeMemory memory) {
		
		Instruction inst = new Instruction();
		int beginningWord = memory.get(instructionPtr);
		int opcode = beginningWord % 100;
		
		inst.type = getTypeFromOpcode(opcode);
		
		int paramModes = beginningWord / 100;
		inst.params = new InstructionParameter[inst.type.getSize() - 1];
		for (int i = 0; i < inst.params.length; i++) {
			inst.params[i] = InstructionParameter.withValueAndMode(memory.get(instructionPtr + i + 1), paramModes % 10 );
			paramModes /= 10;
		}
		
		return inst;
	}
	
	private static Type getTypeFromOpcode(int opcode) {
		switch(opcode) {
		case 1:
			return Type.Add;
		case 2:
			return Type.Multiply;
		case 3:
			return Type.Input;
		case 4:
			return Type.Output;
		case 5:
			return Type.JumpIfTrue;
		case 6: 
			return Type.JumpIfFalse;
		case 7:
			return Type.LessThan;
		case 8:
			return Type.Equals;
		case 99:
			return Type.Halt;
		default:
			throw new RuntimeException("Unknown opcode " + opcode);
		}
	}
	
	public Type getType() {
		return this.type;
	}
	
	public InstructionParameter[] getParams() {
		return this.params;
	}
	
	public int getSize() {
		return this.params.length + 1;
	}
	
	public enum Type {
		
		Add(1, 4),
		Multiply(2, 4),
		Input(3, 2),
		Output(4, 2),
		JumpIfTrue(5, 3),
		JumpIfFalse(6, 3),
		LessThan(7, 4),
		Equals(8, 4),
		Halt(99, 1);
		
		private final int opcode;
		private final int size;
		
		Type(int opcode, int size) {
			this.opcode = opcode;
			this.size = size;
		}
		
		public int getOpcode() {
			return this.opcode;
		}
		
		public int getSize() {
			return this.size;
		}
	}
}
