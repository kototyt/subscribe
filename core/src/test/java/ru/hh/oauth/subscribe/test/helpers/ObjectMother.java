package ru.hh.oauth.subscribe.test.helpers;

import ru.hh.oauth.subscribe.model.OAuthConstants;
import ru.hh.oauth.subscribe.model.OAuthRequest;
import ru.hh.oauth.subscribe.model.Verb;

public class ObjectMother {
  public static OAuthRequest createSampleOAuthRequest() {
    OAuthRequest request = new OAuthRequest(Verb.GET, "http://example.com");
    request.addOAuthParameter(OAuthConstants.TIMESTAMP, "123456");
    request.addOAuthParameter(OAuthConstants.CONSUMER_KEY, "AS#$^*@&");
    request.addOAuthParameter(OAuthConstants.CALLBACK, "http://example/callback");
    request.addOAuthParameter(OAuthConstants.SIGNATURE, "OAuth-Signature");
    return request;
  }
}
