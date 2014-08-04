package ru.hh.oauth.subscribe.extractors;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import ru.hh.oauth.subscribe.model.Token;

public class JsonTokenExtractorTest {
  private String response = "'{ \"access_token\":\"I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T3X\"}'";
  private JsonTokenExtractor extractor = new JsonTokenExtractor();

  @Test
  public void shouldParseResponse() {
    Token token = extractor.extract(response);
    assertEquals(token.getToken(), "I0122HHJKLEM21F3WLPYHDKGKZULAUO4SGMV3ABKFTDT3T3X");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionIfForNullParameters() {
    extractor.extract(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionIfForEmptyStrings() {
    extractor.extract("");
  }
}
