package net.sf.memoranda.testing;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({JNCalendarPanelTest.class})
public class AllTestsSuite {

	@BeforeClass
	public static void setUpClass() {
		System.out.println("Super test case Set Up");
	}
	
	@AfterClass
	public static void tearDownClass() {
		System.out.println("Super test suite Tear Down");
	}
	
}
