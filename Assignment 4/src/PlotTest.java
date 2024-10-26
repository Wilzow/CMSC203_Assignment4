import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlotTest {
	private Plot plot1, plot2, plot3, plot4, plot5, plot6, plot7, plot8;
	
	@BeforeEach
	void setUp() throws Exception {
		plot1 = new Plot(2, 2, 6, 6);
		plot2 = new Plot(3,3,6,6);
		plot3 = new Plot(1,1,1,1);
		plot4 = new Plot(3,3,4,4);
		plot5 = new Plot(3, 4, 4, 3);
		plot6 = new Plot(1,1,2,2);
		plot7 = new Plot(1,2,2,2);
		plot8 = new Plot(2,1,2,2);
	}

	@AfterEach
	void tearDown() throws Exception {
		plot1 = plot5 = null;
	}
	
	@Test
	public void edgeTest() {
		assertTrue(plot2.pointOnEdge(3, 8));
		assertTrue(plot2.pointOnEdge(8, 3));
	}

	@Test
	public void testOverlaps() {
		assertTrue(plot1.overlaps(plot5)); // plot5 is entirely inside plot1
		assertTrue(plot1.overlaps(plot2));
		assertFalse(plot1.overlaps(plot3));
		assertTrue(plot1.pointInPlot(4,3));
		assertTrue(plot6.overlaps(plot7));
		assertTrue(plot6.overlaps(plot8));
	}
	
	@Test
	public void testEncompass() {
		assertTrue(plot1.encompasses(plot5));
		assertTrue(plot1.encompasses(plot4));
		assertFalse(plot1.encompasses(plot2));
	}
	
	@Test
	public void testToString() {
		assertEquals("3,4,4,3",plot5.toString());	
	}

}
