/**
 * 
 */
package net.sf.memoranda.testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.TaskCard;


/**
 * @author trevorforrey
 *
 */
public class TaskCardTest {
	
	TaskCard taskCard;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		taskCard = new TaskCard();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		taskCard = null;
	}

	@Test
	public void test() {
		assertTrue(taskCard.getActualLOC() == 0);
		assertTrue(taskCard.getEstimatedLOC() == 0);
		
		taskCard.setActuaLOC(104);
		assertTrue(taskCard.getActualLOC() == 105);
	}

}
