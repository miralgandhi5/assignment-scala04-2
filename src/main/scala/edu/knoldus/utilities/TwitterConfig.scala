package edu.knoldus.utilities

import com.typesafe.config.{Config, ConfigFactory}
import twitter4j.{Twitter, TwitterFactory}
import twitter4j.conf.{Configuration, ConfigurationBuilder}

object TwitterConfig {

  val Conf: Config = ConfigFactory.load
  val ConfigurationBuilder = new ConfigurationBuilder
  ConfigurationBuilder.setDebugEnabled(true)
  ConfigurationBuilder.setOAuthConsumerKey(Conf.getString("twitter4J.consumerKey.value"))
  ConfigurationBuilder.setOAuthConsumerSecret(Conf.getString("twitter4J.consumerSecret.value"))
  ConfigurationBuilder.setOAuthAccessToken(Conf.getString("twitter4J.accessToken.value"))
  ConfigurationBuilder.setOAuthAccessTokenSecret(Conf.getString("twitter4J.accessSecret.value"))
  val Configuration: Configuration = ConfigurationBuilder.build
  val Twitter: Twitter = new TwitterFactory(Configuration).getInstance
}
