package ru.hh.oauth.subscribe.oauth;

import ru.hh.oauth.subscribe.builder.api.DefaultApi20;
import ru.hh.oauth.subscribe.model.OAuthConfig;
import ru.hh.oauth.subscribe.model.OAuthRequest;
import ru.hh.oauth.subscribe.model.Token;

public class HHOAuthServiceImpl extends OAuth20ServiceImpl {
  public HHOAuthServiceImpl(DefaultApi20 api, OAuthConfig config) {
    super(api, config);
  }

  @Override
  public void signRequest(Token accessToken, OAuthRequest request) {
    request.addHeader("Authorization", "Bearer " + accessToken.getToken());
  }
}
