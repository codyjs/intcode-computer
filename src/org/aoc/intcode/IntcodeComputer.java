package org.aoc.intcode;

public class IntcodeComputer {
	
	private IntcodeMemory memory;
	private int instructionPtr;
	
	public IntcodeComputer(IntcodeMemory memory) {
		this.memory = memory;
		this.instructionPtr = 0;
	}

	public void run() {
		
		while (instructionPtr < memory.size()) {
			
			Instruction instruction = memory.getInstructionAt(instructionPtr);
			
			if (instruction.getType() == Instruction.Type.Halt) return;
			
			execute(instruction);
		}
		
		throw new RuntimeException("ERROR: Reached end of program before halting");
	}
	
	private void execute(Instruction instruction) {
		InstructionParameter[] params = instruction.getParams();
		
		switch (instruction.getType()) {
		case Add:
			if (params[2].getMode() == InstructionParameter.Mode.Immediate)
				System.out.println("WARNING: Destination for ADD instruction is immediate!");
			memory.set(params[2].getValue(), getParamValue(params[0]) + getParamValue(params[1]));
			instructionPtr += instruction.getSize();
			break;
		case Multiply:
			if (params[2].getMode() == InstructionParameter.Mode.Immediate)
				System.out.println("WARNING: Destination for MULTIPLY instruction is immediate!");
			memory.set(params[2].getValue(), getParamValue(params[0]) * getParamValue(params[1]));
			instructionPtr += instruction.getSize();
			break;
		case JumpIfTrue:
			if (getParamValue(params[0]) != 0) {
				instructionPtr = getParamValue(params[1]);
			} else {
				instructionPtr += instruction.getSize();
			}
			break;
		case JumpIfFalse:
			if (getParamValue(params[0]) == 0) {
				instructionPtr = getParamValue(params[1]);
			} else {
				instructionPtr += instruction.getSize();
			}
			break;
		case LessThan:
			if (params[2].getMode() == InstructionParameter.Mode.Immediate)
				System.out.println("WARNING: Destination for LESSTHAN instruction is immediate!");
			if (getParamValue(params[0]) < getParamValue(params[1])) {
				memory.set(params[2].getValue(), 1);
			} else {
				memory.set(params[2].getValue(), 0);
			}
			instructionPtr += instruction.getSize();
			break;
		case Equals:
			if (params[2].getMode() == InstructionParameter.Mode.Immediate)
				System.out.println("WARNING: Destination for EQUALS instruction is immediate!");
			if (getParamValue(params[0]) == getParamValue(params[1])) {
				memory.set(params[2].getValue(), 1);
			} else {
				memory.set(params[2].getValue(), 0);
			}
			instructionPtr += instruction.getSize();
			break;
		case Input:
			if (params[0].getMode() == InstructionParameter.Mode.Immediate)
				System.out.println("WARNING: Destination for INPUT instruction is immediate!");
			String input = "5"; // System.console().readLine();
			memory.set(params[0].getValue(), Integer.parseInt(input));
			instructionPtr += instruction.getSize();
			break;
		case Output:
			System.out.println("> " + getParamValue((params[0])));
			instructionPtr += instruction.getSize();
			break;
		default:
			throw new RuntimeException("ERROR: Cannot execute instruction with opcode " + instruction.getType());
		}
	}
	
	private int getParamValue(InstructionParameter param) {
		if (param.getMode() == InstructionParameter.Mode.Position) {
			return memory.get(param.getValue());
		}
		return param.getValue();
	}

}
