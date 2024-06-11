package org.mine;

import org.junit.*;
import org.mine.LoTR.*;

import java.util.*;

import static org.junit.Assert.*;

public class Junit4AssertTest {
  static ArrayList<Object> fellowshipOfTheRing;
  static String[] HobbitNames;
  static Hobbit frodo;
  static Hobbit sam;
  static Wizard sauron;
  static int age;

  @BeforeClass
  public static void Setup() {
    fellowshipOfTheRing = new ArrayList<Object>();
    HobbitNames = new String[]{"Sam", "Frodo"};
    frodo = new Hobbit("Frodo", 33);
    sam = new Hobbit("Sam");
    sauron = new Wizard("Sauron");
    fellowshipOfTheRing.add(frodo);
    fellowshipOfTheRing.add(sam);
    age = 33;
  }

  @Test
  public void Junit4Test_Basic() {

    // basic assertions
    assertEquals(frodo.getName(), "Frodo");
    assertNotEquals(frodo, sauron);
    assertTrue(frodo.getName()=="Frodo");
    assertFalse(age==32);
    assertNotNull(fellowshipOfTheRing);

    // array assertion
    assertArrayEquals(HobbitNames, HobbitNames);

    // No collection specific assertions, but still possible
    assertEquals(fellowshipOfTheRing.size(), 2);
    assertEquals(fellowshipOfTheRing.contains(frodo) && fellowshipOfTheRing.contains(sam), true);
    assertEquals(fellowshipOfTheRing.contains(sauron), false);

    // messages
    assertEquals("check " + frodo.getName() + "'s age", frodo.getAge(), 33);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionWithExpected() {

    // Code that is expected to throw an Exception (IllegalArgumentException)
    throw new IllegalArgumentException("This is an error message");
  }

  @Test(timeout = 100)
  public void testTimeout() throws InterruptedException {
    Thread.sleep(101); // Example: simulating an unacceptable delay
  }

  @Test(timeout = 100)
  public void testNotTimeout() throws InterruptedException {
    Thread.sleep(80); // Example: simulating an acceptable delay
  }

  @Test
  public void AssertjTest_Messages() {
    assertEquals("assert Frodo's name", frodo.getName(), "Frodo!");
  }
}
