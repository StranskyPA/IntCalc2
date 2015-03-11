package intcalc;

import static org.junit.Assert.*;

import org.junit.*;

import edu.hendrix.grambler.ParseException;

public class EvaluatorTest {
	private Evaluator eval;
	
	@Before
	public void setup() {
		eval = new Evaluator();
	}
	
	@Test
	public void test1() throws ParseException {
		assertEquals("6", eval.eval("3 * 2"));
	}
	
	@Test
	public void test2() throws ParseException {
		assertEquals("5", eval.eval("3 + 2"));
	}
	
	@Test
	public void test3() throws ParseException {
		assertEquals("1", eval.eval("3 - 2"));
	}
	
	@Test
	public void test4() throws ParseException {
		assertEquals("3", eval.eval("10 / 3"));
	}

	@Test
	public void test5() throws ParseException {
		assertEquals("4\n6", eval.eval("x = 4\nx + 2"));
	}

	@Test
	public void test6() throws ParseException {
		assertEquals("4\n5\n20", eval.eval("x = 4\ny = 5\nx * y"));
	}
	
	@Test
	public void test7() throws ParseException {
		assertEquals("12", eval.eval("2 + 5 * 2"));
	}
	
	@Test
	public void test8() throws ParseException {
		assertEquals("14", eval.eval("(2 + 5) * 2"));
	}
	
	@Test
	public void test9() throws ParseException {
		assertEquals("18", eval.eval("(7 - (3 + 2)) * (5 + 4)\n"));
	}
	
	@Test
	public void myTest() throws ParseException {
		assertEquals("4", eval.eval("x = 4"));
	}
}
