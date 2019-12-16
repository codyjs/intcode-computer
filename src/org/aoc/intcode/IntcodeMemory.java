package org.aoc.intcode;

import java.util.ArrayList;
import java.util.List;

public class IntcodeMemory {
	
	private List<Integer> memory = new ArrayList<>();
	
	public IntcodeMemory(List<Integer> memory) {
		this.memory = memory;
	}
	
	public Integer get(int index) {
		if (index >= memory.size()) return null;
		return memory.get(index);
	}
	
	public void set(int index, int element) {
		this.memory.set(index, element);
	}
	
	public int size() {
		return this.memory.size();
	}
	
	public Instruction getInstructionAt(int instructionPtr) {
		return Instruction.fromInstructionPointer(instructionPtr, this);
	}

}
