package org.mine;

import org.junit.Test;
import org.junit.jupiter.api.*;
import org.mine.LoTR.*;

import java.time.*;
import java.util.*;

import static java.lang.Thread.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class Junit4HamcrestAssertTest {
  static ArrayList<Object> fellowshipOfTheRing;
  static String[] HobbitNames;
  static Hobbit frodo;
  static Hobbit sam;
  static Wizard sauron;
  static int age;

  @BeforeAll
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
  public void Junit4AssertTest() {




    // chaining string specific assertions
    /*
        assertThat(frodo.getName()).startsWith("Fro")
                                   .endsWith("do")
                                   .isEqualToIgnoringCase("frodo");
    */

    assertEquals(frodo.getName().substring(0, 3), "Fro");
    assertEquals(frodo.getName().substring("Frodo".length() - 2), "do");
    assertEquals(frodo.getName(), "Frodo");

    // collection specific assertions (there are plenty more)
    /* in the examples below fellowshipOfTheRing is a List<Hobbit>

        assertThat(fellowshipOfTheRing).hasSize(2)
                                       .contains(frodo, sam)
                                       .doesNotContain(sauron);
    */

    assertEquals(fellowshipOfTheRing.size(), 2);
    assertEquals(fellowshipOfTheRing.contains(frodo) && fellowshipOfTheRing.contains(sam), true);
    assertEquals(fellowshipOfTheRing.contains(sauron), false);

    // as() is used to describe the test and will be shown before the error message
    // assertThat(frodo.getAge()).as("check %s's age", frodo.getName()).isEqualTo(33);

    assertEquals("check " + frodo.getName() + "'s age", frodo.getAge(), 33);

  }




  @Test
  public void AssertjTest_Basic() {


    // basic assertions
    assertEquals(frodo.getName(), "Frodo");
    assertNotEquals(frodo, sauron);
    assertTrue(frodo.getName()=="Frodo");
    assertFalse(age==32);
    assertNotNull(fellowshipOfTheRing);
    fail("This test should fail");

    // array assertion
    assertArrayEquals(HobbitNames, HobbitNames);

    // Arrays
    String[] copy = HobbitNames;
    assertArrayEquals(copy, HobbitNames);

    assertEquals("Frodo", "frodo", "Case mismatch");

    // not possible:
    //assertEquals("Frodo", "frodo", () -> "Custom message");
  }

  @Test
  public void AssertjTest_ExtendedDataMethods() {

    // chaining string specific assertions
    assertThat(frodo.getName()).startsWith("Fro").endsWith("do").isEqualToIgnoringCase("frodo");

    // collection specific assertions (there are plenty more)
    // in the examples below fellowshipOfTheRing is a List<Hobbit>
    assertThat(fellowshipOfTheRing).hasSize(2).contains(frodo, sam).doesNotContain(sauron);
  }

  @Test
  public void AssertjTest_GroupedAssertions() {
    Assertions.assertAll("Grouped assertion", () -> Assertions.assertEquals("Frodo", "frodo"), () -> Assertions.assertEquals("Sam", "sam"));
  }

  @Test
  public void AssertjTest_Exceptions() {

    try {
      // Code that is expected to throw the exception
      int i = 1 / 0;

    } catch (Exception e) {
      // Use Assertj to assert properties of the exception
      assertThat(e).hasMessageContaining("/ by zero");
      return; // Test passes if exception is caught
    }

    // If no exception is thrown, the test should fail
    throw new AssertionError("Expected DivisionByZero exception to be thrown");
  }

  @Test
  public void AssertjTest_Timeout() {
    Assertions.assertTimeout(Duration.ofMillis(100), () -> {
      sleep(101);
    });
  }

  @Test
  public void AssertjTest_NotTimeout() {
    Assertions.assertTimeout(Duration.ofMillis(100), () -> {
      sleep(20);
    });
  }

  @Test
  public void AssertjTest_Messages() {
    // as() is used to describe the test and will be shown before the error message
    // assertThat(frodo.getAge()).as("check %s's age", frodo.getName()).isEqualTo(33);
    assertThat(frodo.getAge()).as("check %s's age", frodo.getName()).isEqualTo(33);
  }
}
