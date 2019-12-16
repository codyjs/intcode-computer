package org.aoc.intcode;

public class InstructionParameter {
	
	private int value;
	private Mode mode;
	
	public static InstructionParameter withValueAndMode(int value, int mode) {
		InstructionParameter param = new InstructionParameter();
		
		param.value = value;
		param.mode = mode == 0 ? Mode.Position : Mode.Immediate;
		
		return param;
	}
	
	public int getValue() {
		return this.value;
	}
	
	/**
	 * @return
	 * 
	 * 0 for position mode.
	 * <br>
	 * 1 for immediate mode.
	 */
	public Mode getMode() {
		return this.mode;
	}
	
	public enum Mode {
		Position,
		Immediate
	}

}
