import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author bmbte
 *	I have mostly used this to test corner cases not tested in the public tests.
 */
public class BasicDoubleLinkedList_STUDENT {

	BasicDoubleLinkedList<String> linkedString;
	StringComparator comparator;
	
	@Before
	public void setUp() throws Exception {
		linkedString = new BasicDoubleLinkedList<String>();
		comparator = new StringComparator();
		linkedString.addToEnd("Last");
		linkedString.addToFront("First");
	}

	@After
	public void tearDown() throws Exception {
		linkedString = null;
		comparator = null;
	}

	@Test
	public void testRetrieveFirst() {
		assertEquals("First", linkedString.retrieveFirstElement());
		assertEquals("Last", linkedString.retrieveFirstElement());
	}
	
	@Test
	public void testRetrieveLast() {
		assertEquals("Last", linkedString.retrieveLastElement());
		assertEquals("First", linkedString.retrieveLastElement());
	}
	
	
	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}

}
