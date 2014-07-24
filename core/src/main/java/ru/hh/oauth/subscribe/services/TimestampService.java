package ru.hh.oauth.subscribe.services;

/**
 * Unix epoch timestamp generator. This class is useful for stubbing in tests.
 *
 * @author  Pablo Fernandez
 */
public interface TimestampService {
  /**
   * Returns the unix epoch timestamp in seconds
   *
   * @return  timestamp
   */
  String getTimestampInSeconds();

  /**
   * Returns a nonce (unique value for each request)
   *
   * @return  nonce
   */
  String getNonce();
}
