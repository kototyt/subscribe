package ru.hh.oauth.subscribe.builder.oauth.examples;

import java.util.Scanner;
import ru.hh.oauth.subscribe.builder.ServiceBuilder;
import ru.hh.oauth.subscribe.builder.api.MeetupApi;
import ru.hh.oauth.subscribe.model.OAuthRequest;
import ru.hh.oauth.subscribe.model.Response;
import ru.hh.oauth.subscribe.model.Token;
import ru.hh.oauth.subscribe.model.Verb;
import ru.hh.oauth.subscribe.model.Verifier;
import ru.hh.oauth.subscribe.oauth.OAuthService;

public class MeetupExample {
  private static final String PROTECTED_RESOURCE_URL = "http://api.meetup.com/2/member/self";

  public static void main(String[] args) {
    OAuthService service = new ServiceBuilder().provider(MeetupApi.class)
      .apiKey("j1khkp0dus323ftve0sdcv6ffe")
      .apiSecret("6s6gt6q59gvfjtsvgcmht62gq4")
      .build();
    Scanner in = new Scanner(System.in);

    System.out.println("=== Meetup's OAuth Workflow ===");
    System.out.println();

    // Obtain the Request Token
    System.out.println("Fetching the Request Token...");
    Token requestToken = service.getRequestToken();
    System.out.println("Got the Request Token!");
    System.out.println();

    System.out.println("Now go and authorize SubScribe here:");
    System.out.println(service.getAuthorizationUrl(requestToken));
    System.out.println("And paste the verifier here");
    System.out.print(">>");
    Verifier verifier = new Verifier(in.nextLine());
    System.out.println();

    // Trade the Request Token and Verfier for the Access Token
    System.out.println("Trading the Request Token for an Access Token...");
    Token accessToken = service.getAccessToken(requestToken, verifier);
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
    System.out.println(response.getBody());

    System.out.println();
    System.out.println("Thats it man! Go and build something awesome with SubScribe! :)");
  }
}
