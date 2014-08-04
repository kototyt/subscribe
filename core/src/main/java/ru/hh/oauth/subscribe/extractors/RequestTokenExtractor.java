package ru.hh.oauth.subscribe.extractors;

import ru.hh.oauth.subscribe.model.Token;

/**
 * Simple command object that extracts a {@link Token} from a String
 *
 * @author  Pablo Fernandez
 */
public interface RequestTokenExtractor {
  /**
   * Extracts the request token from the contents of an Http Response
   *
   * @param  response  the contents of the response
   *
   * @return  OAuth access token
   */
  Token extract(String response);
}
