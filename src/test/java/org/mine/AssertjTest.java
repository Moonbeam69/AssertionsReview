package org.mine;

import org.junit.jupiter.api.Test;
import org.mine.LoTR.*;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

public class Assertj {

  @Test
  public void AssertjTest(){

    ArrayList<Object> fellowshipOfTheRing = new ArrayList<Object>();

    Hobbit frodo = new Hobbit("Frodo");
    Hobbit sam = new Hobbit("Sam");
    Wizard sauron = new Wizard("Sauron");
    fellowshipOfTheRing.add(frodo);
    fellowshipOfTheRing.add(sam);

    // basic assertions
    assertThat(frodo.getName()).isEqualTo("Frodo");
    assertThat(frodo).isNotEqualTo(sauron);

    // chaining string specific assertions
    assertThat(frodo.getName()).startsWith("Fro")
            .endsWith("do")
            .isEqualToIgnoringCase("frodo");

    // collection specific assertions (there are plenty more)
    // in the examples below fellowshipOfTheRing is a List<Hobbit>
    assertThat(fellowshipOfTheRing).hasSize(2)
            .contains(frodo, sam)
            .doesNotContain(sauron);

    System.out.println("Done");
  }
}
