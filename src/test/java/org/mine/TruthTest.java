package org.mine;

import org.junit.jupiter.api.*;
import org.mine.LoTR.Hobbit;
import org.mine.LoTR.Wizard;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static java.lang.Thread.sleep;

public class TruthTest {
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
  }

  @Test
  public void TruthTest_Basic() {
    // basic assertions
    assertThat(frodo.getName()).isEqualTo("Frodo");
    assertThat(frodo).isNotEqualTo(sauron);
    //fail("This test should fail");

    // Arrays
    String[] copy = HobbitNames;
    Assertions.assertArrayEquals(copy, HobbitNames);

    Assertions.assertEquals("Frodo", "frodo", "Case mismatch");
    Assertions.assertEquals("Frodo", "frodo", () -> "Custom message");
  }

  @Test
  public void TruthTest_ExtendedDataMethods() {

    // chaining string specific assertions
    assertThat(frodo.getName()).startsWith("Fro");
    assertThat(frodo.getName()).endsWith("do");

    // Not supported
    //assertThat(frodo.getName()).isEqualToIgnoringCase("frodo");

    // collection specific assertions (there are plenty more)
    assertThat(fellowshipOfTheRing).hasSize(2);
    assertThat(fellowshipOfTheRing).contains(frodo);
    assertThat(fellowshipOfTheRing).contains(sam);
    assertThat(fellowshipOfTheRing).doesNotContain(sauron);
  }

  @Test
  public void TruthTest_Fluency() {

    // CANNOT GET THIS TO WORK:

//    List<String> list = Arrays.asList("apple", "banana", "cherry");
//    com.google.common.truth.Truth.assertThat(list)
//            .isNotEmpty()
//            .hasSize(3)
//            .contains("banana")
//            .containsExactly("apple", "banana", "cherry")
//            .inOrder();
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
      assertThat(e).hasMessageThat().contains("/ by zero");
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
    // as() is used to describe the test and will be shown before the error message.
    // TODO why does the following statement not work? .as is not recognised as a valid method
    //com.google.common.truth.Truth.assertThat(frodo.getAge()).as("check Frodos age").isEqualTo(33);
  }

  @Test
  public void SoftAssertjTest_Basic() {
    org.assertj.core.api.SoftAssertions softly = new org.assertj.core.api.SoftAssertions();
    softly.assertThat(frodo.getAge()).isNegative();
    softly.assertThat(frodo.getAge()).isEqualTo(32);
    softly.assertThat(frodo.getName()).isEqualTo("Frodo");
    softly.assertAll();
  }
}