package ru.hh.oauth.subscribe.builder.api.google;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.hh.oauth.subscribe.extractors.JsonTokenExtractor;

/** additionally parses OpenID id_token */
public class GoogleJsonTokenExtractor extends JsonTokenExtractor {
  private static final Pattern ID_TOKEN_PATTERN = Pattern.compile("\"id_token\"\\s*:\\s*\"(\\S*?)\"");

  @Override
  public GoogleToken extract(final String response) {
    return new GoogleToken(extractAccessToken(response), "", response, extractOpenIdToken(response));
  }

  private String extractOpenIdToken(final String response) {
    final Matcher matcher = ID_TOKEN_PATTERN.matcher(response);
    if (matcher.find()) {
      return matcher.group(1);
    }
    return null;
  }
}
