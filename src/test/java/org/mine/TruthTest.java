package org.mine;

import org.junit.jupiter.api.*;
import org.mine.LoTR.*;

import java.time.*;
import java.util.*;

import static java.lang.Thread.*;
import static org.assertj.core.api.Assertions.*;

public class TruthTest {
  static ArrayList<Object> fellowshipOfTheRing;
  static String[] HobbitNames;
  static Hobbit frodo;
  static Hobbit sam;
  static Wizard sauron;

  @BeforeAll
  public static void Setup() {
    fellowshipOfTheRing = new ArrayList<Object>();
    HobbitNames = new String[]{"Sam", "Frodo"};
    frodo = new Hobbit("Frodo", 33);
    sam = new Hobbit("Sam");
    sauron = new Wizard("Sauron");
    fellowshipOfTheRing.add(frodo);
    fellowshipOfTheRing.add(sam);
  }

  @Test
  public void TruthTest_Basic() {
    // basic assertions
    assertThat(frodo.getName()).isEqualTo("Frodo");
    assertThat(frodo).isNotEqualTo(sauron);
    fail("This test should fail");

    // Arrays
    String[] copy = HobbitNames;
    Assertions.assertArrayEquals(copy, HobbitNames);

    Assertions.assertEquals("Frodo", "frodo", "Case mismatch");
    Assertions.assertEquals("Frodo", "frodo", () -> "Custom message");
  }

  @Test
  public void TruthTest_ExtendedDataMethods() {

    // chaining string specific assertions
    assertThat(frodo.getName()).startsWith("Fro").endsWith("do").isEqualToIgnoringCase("frodo");

    // collection specific assertions (there are plenty more)
    // in the examples below fellowshipOfTheRing is a List<Hobbit>
    assertThat(fellowshipOfTheRing).hasSize(2).contains(frodo, sam).doesNotContain(sauron);
  }

  @Test
  public void TruthTest_GroupedAssertions() {
    Assertions.assertAll("Grouped assertion", () -> Assertions.assertEquals("Frodo", "frodo"), () -> Assertions.assertEquals("Sam", "sam"));
  }

  @Test
  public void TruthTest_Exceptions() {

    try {
      // Code that is expected to throw the exception
      int i = 1 / 0;

    } catch (Exception e) {
      // Use Truth to assert properties of the exception
      assertThat(e).hasMessageContaining("/ by zero");
      return; // Test passes if exception is caught
    }

    // If no exception is thrown, the test should fail
    throw new AssertionError("Expected DivisionByZero exception to be thrown");
  }

  @Test
  public void TruthTest_Timeout() {
    Assertions.assertTimeout(Duration.ofMillis(100), () -> {
      sleep(101);
    });
  }

  @Test
  public void TruthTest_NotTimeout() {
    Assertions.assertTimeout(Duration.ofMillis(100), () -> {
      sleep(80);
    });
  }

  @Test
  public void TruthTest_Messages() {
    // as() is used to describe the test and will be shown before the error message
    // assertThat(frodo.getAge()).as("check %s's age", frodo.getName()).isEqualTo(33);
    assertThat(frodo.getAge()).as("check %s's age", frodo.getName()).isEqualTo(33);
  }

}