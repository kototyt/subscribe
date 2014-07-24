package ru.hh.oauth.subscribe.builder.api;

import ru.hh.oauth.subscribe.model.OAuthConfig;
import ru.hh.oauth.subscribe.oauth.OAuthService;

/**
 * Contains all the configuration needed to instantiate a valid {@link OAuthService}
 *
 * @author  Pablo Fernandez
 */
public interface Api {
  /**
   * @param  config
   *
   * @return  fully configured {@link OAuthService}
   */
  OAuthService createService(OAuthConfig config);
}
