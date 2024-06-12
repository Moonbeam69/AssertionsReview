package org.mine;

import org.junit.jupiter.api.*;
import org.mine.LoTR.Hobbit;
import org.mine.LoTR.Wizard;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

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
  public void Test_Basic() {

    // basic assertions
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
