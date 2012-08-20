package hr.element.hmtt.data.web

import scala.xml.Node

class Team(
    val teamID           : Int,
    val teamName         : String,
    val assistantTrainers: Int,
    val psychologists    : Int,
    val pressSpokesmen   : Int,
    val physiotherapists : Int,
    val doctors          : Int)
{
}

object Team{

  def apply(elem: Node): Team = {
    def s(tag: String) = (elem \\ tag).text
    def i(tag: String) = s(tag).toInt

    new Team(
        teamID            = i("TeamID"),
        teamName          = s("TeamName"),
        assistantTrainers = i("AssistantTrainers"),
        psychologists     = i("Psychologists"),
        pressSpokesmen    = i("PressSpokesmen"),
        physiotherapists  = i("Physiotherapists"),
        doctors           = i("Doctors")
        )
  }
}