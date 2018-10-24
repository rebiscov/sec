package io.github.mosser.arduinoml.ens.model;

import io.github.mosser.arduinoml.ens.generator.Visitor;

public class SevenSegAction extends Action {

	private SEVENSEGNUMBER value;
	private SevenSeg sevenSeg;
	
	public SevenSegAction() {}

	public SevenSegAction(SevenSeg sevenSeg, SEVENSEGNUMBER value) {
		this.sevenSeg = sevenSeg;
		this.value = value;
	}

	public SevenSegAction(SevenSeg sevenSeg, Integer value) {
		this.sevenSeg = sevenSeg;
		SevenSegNbInt converter = new SevenSegNbInt();
		this.value = converter.fromint(value);
	}
	
	public SEVENSEGNUMBER getValue() {
		return value;
	}

	public void setValue(SEVENSEGNUMBER value) {
		this.value = value;
	}

	public int getValueInt() {
		SevenSegNbInt converter = new SevenSegNbInt();
		return converter.toint(value);

	}

	public void setValueInt(int value) {
		SevenSegNbInt converter = new SevenSegNbInt();
		this.value = converter.fromint(value);
	}

	public SevenSeg getSevenSeg() {
		return sevenSeg;
	}

	public void setSevenSeg(SevenSeg sevenSeg) {
		this.sevenSeg = sevenSeg;
	}

	public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class SevenSegNbInt {
	public int toint(SEVENSEGNUMBER nb) {
		switch (nb) {
			case ZERO: return 0;
			case ONE: return 1;
			case TWO: return 2;
			case THREE: return 3;
			case FOUR: return 4;
			case FIVE: return 5;
			case SIX: return 6;
			case SEVEN: return 7;
			case EIGHT: return 8;
			case NINE: return 9;
		}
		return -1;
	}

	public SEVENSEGNUMBER fromint(int nb) {
		switch (nb) {
			case 1: return SEVENSEGNUMBER.ONE;
			case 2: return SEVENSEGNUMBER.TWO;
			case 3: return SEVENSEGNUMBER.THREE;
			case 4: return SEVENSEGNUMBER.FOUR;
			case 5: return SEVENSEGNUMBER.FIVE;
			case 6: return SEVENSEGNUMBER.SIX;
			case 7: return SEVENSEGNUMBER.SEVEN;
			case 8: return SEVENSEGNUMBER.EIGHT;
			case 9: return SEVENSEGNUMBER.NINE;
			default: return SEVENSEGNUMBER.ZERO;
		}
	}

}