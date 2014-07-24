package ru.hh.oauth.subscribe.builder.api;

import ru.hh.oauth.subscribe.extractors.AccessTokenExtractor;
import ru.hh.oauth.subscribe.extractors.JsonTokenExtractor;
import ru.hh.oauth.subscribe.model.OAuthConfig;
import ru.hh.oauth.subscribe.model.Verb;
import ru.hh.oauth.subscribe.oauth.MailruOAuthServiceImpl;
import ru.hh.oauth.subscribe.oauth.OAuthService;
import ru.hh.oauth.subscribe.utils.OAuthEncoder;
import ru.hh.oauth.subscribe.utils.Preconditions;

public class MailruApi extends DefaultApi20 {
  private static final String AUTHORIZE_URL = "https://connect.mail.ru/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code";
  private static final String SCOPED_AUTHORIZE_URL = AUTHORIZE_URL + "&scope=%s";

  @Override
  public Verb getAccessTokenVerb() {
    return Verb.POST;
  }

  @Override
  public String getAccessTokenEndpoint() {
    return "https://connect.mail.ru/oauth/token";
  }

  @Override
  public String getAuthorizationUrl(OAuthConfig config) {
    Preconditions.checkValidUrl(config.getCallback(), "Valid url is required for a callback. Mail.ru does not support OOB");
    if (config.hasScope()) { // Appending scope if present
      return String.format(
        SCOPED_AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()), OAuthEncoder.encode(config.getScope()));
    } else {
      return String.format(AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()));
    }
  }

  @Override
  public OAuthService createService(OAuthConfig config) {
    return new MailruOAuthServiceImpl(this, config);
  }

  @Override
  public AccessTokenExtractor getAccessTokenExtractor() {
    return new JsonTokenExtractor();
  }
}
