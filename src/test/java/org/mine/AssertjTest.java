package org.mine;

import org.mine.LoTR.Wizard;

import java.time.Duration;
import java.util.ArrayList;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import static org.junit.jupiter.api.Assertions.*;

public class AssertjTest {
  static ArrayList<Object> fellowshipOfTheRing;
  static String[] HobbitNames;
  static org.mine.LoTR.Hobbit frodo;
  static org.mine.LoTR.Hobbit sam;
  static Wizard sauron;
  static int age;

  @BeforeAll
  public static void Setup() {
    fellowshipOfTheRing = new ArrayList<Object>();
    HobbitNames = new String[]{"Sam", "Frodo"};
    age = 33;
    frodo = new org.mine.LoTR.Hobbit("Frodo", age);
    sam = new org.mine.LoTR.Hobbit("Sam");
    sauron = new Wizard("Sauron");
    fellowshipOfTheRing.add(frodo);
    fellowshipOfTheRing.add(sam);
  }

  @Test
  public void AssertjTest_Basic() {
    // basic assertions
    assertThat(frodo.getName()).isEqualTo("Frodo");
    assertThat(frodo).isNotEqualTo(sauron);
    fail("This test should fail");

    // Arrays
    String[] copy = HobbitNames;
    ArrayList<Object> anotherFellowship = new ArrayList<Object>();
    anotherFellowship.add(frodo);
    anotherFellowship.add(sam);

    assertThat(copy).isEqualTo(HobbitNames);
    assertThat(anotherFellowship).isEqualTo(HobbitNames);

    assertEquals("Frodo", "frodo", "Case mismatch");
    assertEquals("Frodo", "frodo", () -> "Custom message");
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
    org.junit.jupiter.api.Assertions.assertAll("Grouped assertion", () -> assertEquals("Frodo", "Frodo"), () -> assertEquals("Sam", "Sam"));
  }

  @Test
  public void AssertjTest_Exceptions() {

    try {
      // Code that is expected to throw the exception
      int i = 1 / 0;

    } catch (Exception e) {
      assertThat(e).hasMessageContaining("/ by zero");
      return;
    }

    // If no exception is thrown, the test should fail
    throw new AssertionError("Expected DivisionByZero exception to be thrown");
  }

  @Test
  public void AssertjTest_MustTimeout() {
      assertTimeout(Duration.ofMillis(100), () -> {
      sleep(101);
    });
  }

  @Test
  public void AssertjTest_NotTimeout() {
    org.junit.jupiter.api.Assertions.assertTimeout(Duration.ofMillis(100), () -> {
      sleep(80);
    });
  }

  @Test
  public void AssertjTest_Messages() {
    // as() is used to describe the test and will be shown before the error message
    assertThat(frodo.getAge()).as("check %s's age", frodo.getName()).isEqualTo(33);
  }

  @Test
  public void SoftAssertjTest_Basic() {
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(frodo.getAge()).isNegative();
    softly.assertThat(frodo.getAge()).isEqualTo(32);
    softly.assertThat(frodo.getName()).isEqualTo("Frodo");
    softly.assertAll();
  }

}