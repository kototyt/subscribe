package ru.hh.oauth.subscribe.builder.api;

import ru.hh.oauth.subscribe.extractors.AccessTokenExtractor;
import ru.hh.oauth.subscribe.extractors.JsonTokenExtractor;
import ru.hh.oauth.subscribe.model.OAuthConfig;
import ru.hh.oauth.subscribe.utils.OAuthEncoder;
import ru.hh.oauth.subscribe.utils.Preconditions;

/**
 * @author  Boris G. Tsirkin <mail@dotbg.name>
 * @since  20.4.2011
 */
public class VkontakteApi extends DefaultApi20 {
  private static final String AUTHORIZE_URL = "https://oauth.vk.com/authorize?client_id=%s&redirect_uri=%s&response_type=code";
  private static final String SCOPED_AUTHORIZE_URL = String.format("%s&scope=%%s", AUTHORIZE_URL);

  @Override
  public String getAccessTokenEndpoint() {
    return "https://oauth.vk.com/access_token";
  }

  @Override
  public String getAuthorizationUrl(OAuthConfig config) {
    Preconditions.checkValidUrl(config.getCallback(), "Valid url is required for a callback. Vkontakte does not support OOB");
    if (config.hasScope()) { // Appending scope if present
      return String.format(
        SCOPED_AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()), OAuthEncoder.encode(config.getScope()));
    } else {
      return String.format(AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()));
    }
  }

  @Override
  public AccessTokenExtractor getAccessTokenExtractor() {
    return new JsonTokenExtractor();
  }
}