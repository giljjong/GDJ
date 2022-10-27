package junit;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void 합계테스트1() {
		Calculator cal = new Calculator();
		assertEquals(12, cal.add(5, 7));
	}
	
	@Test
	public void 합계테스트2() {
		Calculator cal = new Calculator();
		assertEquals(35, cal.mul(5, 7));
	}

}
