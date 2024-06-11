package org.mine;

import org.junit.*;
import org.mine.LoTR.*;

import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class Junit5AssertTest {

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

    @org.junit.jupiter.api.Test
    public void testBasic() {

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
      assertEquals(frodo.getAge(), 33, "check " + frodo.getName() + "'s age");

    }
    @org.junit.jupiter.api.Test
    public void testExpectedExceptions() {
      assertThrows(  Exception.class,   () -> {
        throw new Exception("This is an error message");
      });
    }


    @org.junit.jupiter.api.Test
    public void testTimeout()  {
      assertTimeout(Duration.ofSeconds(2), () -> {
        // Code that should complete within 2   seconds
        Thread.sleep(1000);
      });

    }

  @org.junit.jupiter.api.Test
    public void testNotTimeout() throws InterruptedException {
    assertTimeout(Duration.ofMillis(1000), () -> {
      // Code that should complete within 2   seconds
      Thread.sleep(900);
    });
    }

    @org.junit.Test
    public void testAssertMessage() {
      assertEquals("assert Frodo's name", frodo.getName(), "Frodo!");
    }
}
