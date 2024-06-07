package org.mine;

import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.*;


public class TruthAssert {

  @Test
  public void TruthTest() {

    String string = "awesome";
    assertThat(string).startsWith("awet");
    assertWithMessage("Without me, it's just aweso")
            .that(string)
            .contains("me");
  }

}