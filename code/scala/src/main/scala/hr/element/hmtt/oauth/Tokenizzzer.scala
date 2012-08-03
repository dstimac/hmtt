package hr.element.hmtt.oauth

import java.net.URI
import org.scribe.oauth.OAuthService
import org.scribe.builder.ServiceBuilder
import org.scribe.model.Verifier
import org.scribe.model.Token
import java.util.Scanner
import java.io.FileWriter
import scala.xml.XML
import org.scribe.model.OAuthRequest
import org.scribe.model.Verb
import hr.element.hmtt.oauth.api.HattrickApi
import hr.element.hmtt.db.SDM

object Tokenizzzer {

  val accessTokenPath = "/accessToken.xml"
  val service = getService

  def getService = {
    val serviceBuilder = new ServiceBuilder
    serviceBuilder.provider(HattrickApi)
    serviceBuilder.apiKey(OAuthConsumerCredentials.ConsumerKey)
    serviceBuilder.apiSecret(OAuthConsumerCredentials.ConsumerSecret)
    serviceBuilder.build
  }

  def getAuthUrl(service: OAuthService, token: Token) = {
    val authUrl = service.getAuthorizationUrl(token)
    println(authUrl)
  }

  def getAccessToken = {
    if(checkLocalToken) {
      getLocalToken
    } else {
	  val requestToken = service.getRequestToken
	  getAuthUrl(service, requestToken)
	  val in = io.Source.stdin.getLines()
	  val line = in.next
	  val v = new Verifier(line)
	  val accessToken = service.getAccessToken(requestToken, v)
	  saveToken(accessToken)
	  accessToken
    }
  }

  def checkLocalToken = {
    val iS = this.getClass().getResourceAsStream(accessTokenPath)
    val s = scala.io.Source.fromInputStream(iS).mkString
    iS.close()
    val tokenXML = XML.loadString(s)
    if( ((tokenXML \ "oauth_token") toString).size > 30 ) {
      true
    }else false
  }

  def saveToken(token: Token) {
    val fileWriter = new FileWriter("src/main/resources"+accessTokenPath)
    val formatedXML = formatXMLToken(token)
    fileWriter.write(formatedXML)
    fileWriter.close()

    SDM.insertToken(token.getToken, token.getSecret)
  }

  def formatXMLToken(token: Token): String = {
    val xmlToken =
      "<token>\n" +
        "<oauth_token>" + token.getToken + "</oauth_token>\n" +
        "<oauth_token_secret>" + token.getSecret + "</oauth_token_secret>\n" +
      "</token>\n"
    xmlToken
  }

  def getLocalToken = {
    val iS = this.getClass().getResourceAsStream(accessTokenPath)
    val s = scala.io.Source.fromInputStream(iS).mkString
    iS.close()
    val tokenXML = XML.loadString(s)
    val token = new Token((tokenXML \ "oauth_token") text
        , (tokenXML \ "oauth_token_secret") text)
    token
  }

  def getResource(resource: String): String  = {
    val request = new OAuthRequest(Verb.GET, resource)
    service.signRequest(getAccessToken, request)
    val response = request.send
    response.getBody
  }
}