package se.citerus.dddsample.domain.model.cargo;

import static org.assertj.core.api.Assertions.fail;

import org.junit.Test;

import se.citerus.dddsample.domain.model.cargo.vo.TrackingId;

public class TrackingIdTest {

  @Test
  public void testConstructor() {
    try {
      new TrackingId(null);
      fail("Should not accept null constructor arguments");
    } catch (IllegalArgumentException expected) {}
  }

}
