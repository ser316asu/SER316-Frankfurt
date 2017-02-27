/**
 * 
 */
package net.sf.memoranda.testing;

import static org.junit.Assert.*;

import java.awt.Dimension;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.ui.JNCalendarPanel;

/**
 * @author trevorforrey
 *
 */
public class JNCalendarPanelTest {

	private JNCalendarPanel testPanel;
	private JNCalendarPanel samePanel;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testPanel = testPanel.getInstance();
		samePanel = samePanel.getInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		testPanel = null;
	}

	@Test
	public void testSingleton() {
		testPanel.setSize(new Dimension(100,100));
		assertEquals(testPanel.getSize(), samePanel.getSize());
		testPanel.setAutoscrolls(true);
		assertTrue(samePanel.getAutoscrolls());
	}

}
