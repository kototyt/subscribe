package ru.hh.oauth.subscribe.extractors;

import java.util.Map;
import ru.hh.oauth.subscribe.exceptions.OAuthParametersMissingException;
import ru.hh.oauth.subscribe.model.OAuthRequest;
import ru.hh.oauth.subscribe.utils.OAuthEncoder;
import ru.hh.oauth.subscribe.utils.Preconditions;

/**
 * Default implementation of {@link HeaderExtractor}. Conforms to OAuth 1.0a
 *
 * @author  Pablo Fernandez
 */
public class HeaderExtractorImpl implements HeaderExtractor {
  private static final String PARAM_SEPARATOR = ", ";
  private static final String PREAMBLE = "OAuth ";
  public static final int ESTIMATED_PARAM_LENGTH = 20;

  /** {@inheritDoc} */
  public String extract(OAuthRequest request) {
    checkPreconditions(request);
    Map<String, String> parameters = request.getOauthParameters();
    StringBuilder header = new StringBuilder(parameters.size() * ESTIMATED_PARAM_LENGTH);
    header.append(PREAMBLE);
    for (Map.Entry<String, String> entry : parameters.entrySet()) {
      if (header.length() > PREAMBLE.length()) {
        header.append(PARAM_SEPARATOR);
      }
      header.append(String.format("%s=\"%s\"", entry.getKey(), OAuthEncoder.encode(entry.getValue())));
    }
    return header.toString();
  }

  private void checkPreconditions(OAuthRequest request) {
    Preconditions.checkNotNull(request, "Cannot extract a header from a null object");

    if (request.getOauthParameters() == null || request.getOauthParameters().size() <= 0) {
      throw new OAuthParametersMissingException(request);
    }
  }
}
