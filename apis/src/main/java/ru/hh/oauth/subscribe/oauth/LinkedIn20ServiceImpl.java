package ru.hh.oauth.subscribe.oauth;

import ru.hh.oauth.subscribe.builder.api.DefaultApi20;
import ru.hh.oauth.subscribe.model.OAuthConfig;
import ru.hh.oauth.subscribe.model.OAuthRequest;
import ru.hh.oauth.subscribe.model.Token;

public class LinkedIn20ServiceImpl extends OAuth20ServiceImpl {
  public LinkedIn20ServiceImpl(final DefaultApi20 api, final OAuthConfig config) {
    super(api, config);
  }

  @Override
  public void signRequest(Token accessToken, OAuthRequest request) {
    request.addQuerystringParameter("oauth2_access_token", accessToken.getToken());
  }
}
