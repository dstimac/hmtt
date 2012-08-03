package hr.element.hmtt.oauth.api

import org.scribe.builder.api.DefaultApi10a
import org.scribe.extractors.AccessTokenExtractor
import org.scribe.extractors.BaseStringExtractor
import org.scribe.extractors.BaseStringExtractorImpl
import org.scribe.extractors.HeaderExtractor
import org.scribe.extractors.HeaderExtractorImpl
import org.scribe.extractors.RequestTokenExtractor
import org.scribe.extractors.TokenExtractorImpl
import org.scribe.model.OAuthConfig
import org.scribe.model.Token
import org.scribe.model.Verb
import org.scribe.oauth.OAuth10aServiceImpl
import org.scribe.oauth.OAuthService
import org.scribe.services.HMACSha1SignatureService
import org.scribe.services.SignatureService
import org.scribe.services.TimestampService
import org.scribe.services.TimestampServiceImpl

abstract class DefaultApi10aWithGet extends DefaultApi10a {

  override def getAccessTokenExtractor(): AccessTokenExtractor = {
    new TokenExtractorImpl
  }

  override def getBaseStringExtractor(): BaseStringExtractor = {
    new BaseStringExtractorImpl
  }

  override def getHeaderExtractor(): HeaderExtractor = {
    new HeaderExtractorImpl
  }

  override def getRequestTokenExtractor(): RequestTokenExtractor = {
    new TokenExtractorImpl
  }

  override def getSignatureService(): SignatureService = {
    new HMACSha1SignatureService
  }

  override def getTimestampService(): TimestampService = {
    new TimestampServiceImpl
  }

  override def getAccessTokenVerb(): Verb = {
    Verb.GET
  }

  override def getRequestTokenVerb(): Verb = {
    Verb.GET
  }

  def getRequestTokenEndpoint(): String

  def getAuthorizationUrl(requestToken: Token): String

  override def createService(config: OAuthConfig): OAuthService = {
    new OAuth10aServiceImpl(this, config)
  }

}