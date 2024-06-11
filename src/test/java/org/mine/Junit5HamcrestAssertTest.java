package org.mine;

import org.junit.jupiter.api.*;
import org.mine.LoTR.*;

import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class Junit5HamcrestAssertTest {

  @Test
  public void Junit5AssertTest() {

    ArrayList<Object> fellowshipOfTheRing = new ArrayList<Object>();

    Hobbit frodo = new Hobbit("Frodo", 33);
    Hobbit sam = new Hobbit("Sam");
    Wizard sauron = new Wizard("Sauron");
    fellowshipOfTheRing.add(frodo);
    fellowshipOfTheRing.add(sam);

    // basic assertions
    /*
       assertThat(frodo.getName()).isEqualTo("Frodo");
       assertThat(frodo).isNotEqualTo(sauron);
    */
    assertEquals(frodo.getName(), "Frodo");
    assertNotEquals(frodo, sauron);

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

    assertEquals(frodo.getAge(), 33, "check " + frodo.getName() + "'s age");

  }

  @Test
  public void AssertjTest_GroupedAssertions() {
    Assertions.assertAll("Grouped assertion", () -> Assertions.assertEquals("Frodo", "frodo"), () -> Assertions.assertEquals("Sam", "sam"));
  }

  @Test
  public void Junit5AssertHamcrestTest() {
    //JUnit test using Hamcrest matchers

    ArrayList<Object> fellowshipOfTheRing = new ArrayList<Object>();

    Hobbit frodo = new Hobbit("Frodo",33);
    Hobbit sam = new Hobbit("Sam");
    Wizard sauron = new Wizard("Sauron");
    fellowshipOfTheRing.add(frodo);
    fellowshipOfTheRing.add(sam);

    // basic assertions
    /*
       assertThat(frodo.getName()).isEqualTo("Frodo");
       assertThat(frodo).isNotEqualTo(sauron);
    */
    assertThat(frodo.getName(), is(equalTo("Frodo")));
    assertThat(frodo, is(not(sauron)));

    // chaining string specific assertions
    /*
        assertThat(frodo.getName()).startsWith("Fro")
                                   .endsWith("do")
                                   .isEqualToIgnoringCase("frodo");
    */

    assertThat(frodo.getName().substring(0, 3),  is(equalTo("Fro")));
    assertThat(frodo.getName().substring("Frodo".length() - 2), is(equalTo("do")));
    assertEquals(frodo.getName().toLowerCase(), "Frodo".toLowerCase());

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

    assertEquals(frodo.getAge(), 33, "check " + frodo.getName() + "'s age");

  }
}
