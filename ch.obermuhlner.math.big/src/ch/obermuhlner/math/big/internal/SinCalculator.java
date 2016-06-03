package ch.obermuhlner.math.big.internal;

import java.math.BigDecimal;
import java.math.MathContext;

import ch.obermuhlner.math.big.BigRational;

public class SinCalculator extends SeriesCalculator {

	public static final SinCalculator INSTANCE = new SinCalculator();
	
	private int n = 0;
	boolean negative = false;
	BigRational factorial2nPlus1 = BigRational.ONE;
	
	private SinCalculator() {
		// prevent instances
	}
	
	@Override
	protected BigRational getCurrectFactor() {
		BigRational factor = factorial2nPlus1.reciprocal();
		if (negative) {
			factor = factor.negate();
		}
		return factor;
	}
	
	@Override
	protected void calculateNextFactor() {
		n++;
		factorial2nPlus1 = factorial2nPlus1.multiply(2 * n);
		factorial2nPlus1 = factorial2nPlus1.multiply(2 * n + 1);
		negative = !negative;
	}
	
	@Override
	protected BigDecimal initialPower(BigDecimal x, MathContext mathContext) {
		return x;
	}

	@Override
	protected BigDecimal nextPower(BigDecimal xToThePreviousPower, BigDecimal x, MathContext mathContext) {
		return xToThePreviousPower.multiply(x, mathContext).multiply(x, mathContext);
	}
}
