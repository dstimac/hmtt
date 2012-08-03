package hr.element.hmtt.oauth

object Resources {

  val path = "http://chpp.hattrick.org/chppxml.ashx"

  val players =
    path + "?file=players&version=2.2&actionType=view&includeMatchInfo=True"

  val training =
    path + "?file=training&version=1.9&actionType=view"

}