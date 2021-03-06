package org.scribe.oauth;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verifier;

public class LinkedIn20ServiceImpl extends OAuth20ServiceImpl {

    public LinkedIn20ServiceImpl(final DefaultApi20 api, final OAuthConfig config) {
        super(api, config);
    }

    @Override
    public void signRequest(Token accessToken, OAuthRequest request) {
        request.addQuerystringParameter("oauth2_access_token", accessToken.getToken());
    }

    @Override
    protected OAuthRequest createAccessTokenRequest(final Verifier verifier) {
        final OAuthRequest request = super.createAccessTokenRequest(verifier);
        if (!getConfig().hasGrantType()) {
            request.addParameter(OAuthConstants.GRANT_TYPE, "authorization_code");
        }
        return request;
    }
}
