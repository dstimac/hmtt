package hr.element.hmtt.oauth.api

import org.scribe.model.Token

object HattrickApi extends DefaultApi10aWithGet {

  val authenticatePath =
    "https://chpp.hattrick.org/oauth/authenticate.aspx"

  val authorizePath =
    "https://chpp.hattrick.org/oauth/authorize.aspx?oauth_token=%s"

  def getRequestTokenEndpoint(): String =
    "https://chpp.hattrick.org/oauth/request_token.ashx"

  def getAccessTokenEndpoint(): String =
    "https://chpp.hattrick.org/oauth/access_token.ashx"

  def getAuthorizationUrl(token: Token): String = {
    String.format(authorizePath, token.getToken)
  }

}