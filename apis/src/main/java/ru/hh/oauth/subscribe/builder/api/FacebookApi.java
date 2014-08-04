package ru.hh.oauth.subscribe.builder.api;

import ru.hh.oauth.subscribe.model.OAuthConfig;
import ru.hh.oauth.subscribe.utils.OAuthEncoder;
import ru.hh.oauth.subscribe.utils.Preconditions;

public class FacebookApi extends DefaultApi20 {
  private static final String AUTHORIZE_URL = "https://www.facebook.com/v2.0/dialog/oauth?client_id=%s&redirect_uri=%s";
  private static final String PARAM_SCOPE = "scope";
  private static final String PARAM_STATE = "state";

  @Override
  public String getAccessTokenEndpoint() {
    return "https://graph.facebook.com/v2.0/oauth/access_token";
  }

  @Override
  public String getAuthorizationUrl(final OAuthConfig config) {
    Preconditions.checkValidUrl(config.getCallback(), "Must provide a valid url as callback. Facebook does not support OOB");
    final StringBuilder sb = new StringBuilder(String.format(AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback())));
    if (config.hasScope()) {
      sb.append('&').append(PARAM_SCOPE).append('=').append(OAuthEncoder.encode(config.getScope()));
    }

    final String state = config.getState();
    if (state != null) {
      sb.append('&').append(PARAM_STATE).append('=').append(OAuthEncoder.encode(state));
    }
    return sb.toString();
  }
}
