package org.mine;

import org.junit.jupiter.api.*;
import org.mine.LoTR.*;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class Junit5Assert {

  @Test
  public void Junit5AssertTest() {

    ArrayList<Object> fellowshipOfTheRing = new ArrayList<Object>();

    Hobbit frodo = new Hobbit("Frodo");
    Hobbit sam = new Hobbit("Sam");
    Wizard sauron = new Wizard("Sauron");
    fellowshipOfTheRing.add(frodo);
    fellowshipOfTheRing.add(sam);

    assertEquals(frodo.getName(), "Frodo");

  }
}
