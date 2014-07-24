package ru.hh.oauth.subscribe.builder.oauth.examples;

import java.util.Scanner;
import ru.hh.oauth.subscribe.builder.ServiceBuilder;
import ru.hh.oauth.subscribe.builder.api.SinaWeiboApi20;
import ru.hh.oauth.subscribe.model.OAuthRequest;
import ru.hh.oauth.subscribe.model.Response;
import ru.hh.oauth.subscribe.model.Token;
import ru.hh.oauth.subscribe.model.Verb;
import ru.hh.oauth.subscribe.model.Verifier;
import ru.hh.oauth.subscribe.oauth.OAuthService;

public class SinaWeibo2Example {
  private static final String NETWORK_NAME = "SinaWeibo";
  private static final String PROTECTED_RESOURCE_URL = "https://api.weibo.com/2/account/get_uid.json";
  private static final Token EMPTY_TOKEN = null;

  public static void main(String[] args) {
    // Replace these with your own api key and secret
    String apiKey = "your_api_key";
    String apiSecret = "your_api_secret";
    OAuthService service = new ServiceBuilder().provider(SinaWeiboApi20.class)
      .apiKey(apiKey)
      .apiSecret(apiSecret)
      .callback("http://www.dajie.com/oauth/sina")
      .build();
    Scanner in = new Scanner(System.in);

    System.out.println("=== " + NETWORK_NAME + "'s OAuth Workflow ===");
    System.out.println();

    // Obtain the Authorization URL
    System.out.println("Fetching the Authorization URL...");
    String authorizationUrl = service.getAuthorizationUrl(EMPTY_TOKEN);
    System.out.println("Got the Authorization URL!");
    System.out.println("Now go and authorize SubScribe here:");
    System.out.println(authorizationUrl);
    System.out.println("And paste the authorization code here");
    System.out.print(">>");
    Verifier verifier = new Verifier(in.nextLine());
    System.out.println();

    // Trade the Request Token and Verifier for the Access Token
    System.out.println("Trading the Request Token for an Access Token...");
    Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);
    System.out.println("Got the Access Token!");
    System.out.println("(if your curious it looks like this: " + accessToken + " )");
    System.out.println();

    // Now let's go and ask for a protected resource!
    System.out.println("Now we're going to access a protected resource...");
    OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
    service.signRequest(accessToken, request);
    Response response = request.send();
    System.out.println("Got it! Lets see what we found...");
    System.out.println();
    System.out.println(response.getCode());
    System.out.println(response.getBody());

    System.out.println();
    System.out.println("Thats it man! Go and build something awesome with SubScribe! :)");
  }
}
