package ru.hh.oauth.subscribe.builder.api;

import ru.hh.oauth.subscribe.extractors.AccessTokenExtractor;
import ru.hh.oauth.subscribe.extractors.JsonTokenExtractor;
import ru.hh.oauth.subscribe.model.OAuthConfig;
import ru.hh.oauth.subscribe.model.Verb;
import ru.hh.oauth.subscribe.oauth.HHOAuthServiceImpl;
import ru.hh.oauth.subscribe.oauth.OAuthService;

public class HHApi extends DefaultApi20 {
  private static final String AUTHORIZE_URL = "https://m.hh.ru/oauth/authorize?response_type=code&client_id=%s";
  private static final String TOKEN_URL = "https://m.hh.ru/oauth/token?grant_type=authorization_code";

  public Verb getAccessTokenVerb() {
    return Verb.POST;
  }

  @Override
  public String getAccessTokenEndpoint() {
    return TOKEN_URL;
  }

  @Override
  public String getAuthorizationUrl(OAuthConfig config) {
    return String.format(AUTHORIZE_URL, config.getApiKey());
  }

  @Override
  public AccessTokenExtractor getAccessTokenExtractor() {
    return new JsonTokenExtractor();
  }

  @Override
  public OAuthService createService(OAuthConfig config) {
    return new HHOAuthServiceImpl(this, config);
  }
}
