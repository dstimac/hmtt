package hr.element.hmtt.data.web

import hr.element.hmtt.oauth.Resources
import hr.element.hmtt.oauth.Tokenizzzer
import hr.element.hmtt.db.SDM
import scala.xml.XML
import scala.xml.Elem

object DataLoader {

  def loadPlayers {

    val webResurce = Tokenizzzer.getResource(Resources.players)
    val feedXML = XML.loadString(webResurce)
    val players = extractPlayersFromXML(feedXML)

    for(p <- players) {
      SDM.insertPlayer(p)
      if(p.trainerData.trainerSkill != -1) SDM.insertTrainerInfo(p)
    }
  }

  def loadTraining {

    val webResurce = Tokenizzzer.getResource(Resources.training)
    val feedXML = XML.loadString(webResurce)
    val training = extractTrainingFromXML(feedXML)

    for(t <- training) {
      SDM.insertTraining(t)
    }

  }

  def loadTeam {

    val webResurce = Tokenizzzer.getResource(Resources.team)
    val feedXML = XML.loadString(webResurce)
    val team = extractTeamFromXML(feedXML)

    for(t <- team) {
      SDM.insertTeam(t)
    }
  }

  def batchLoad {
    loadPlayers
    loadTraining
    loadTeam
  }

  def extractPlayersFromXML(feed: Elem) = feed \\ "Player" map Player.apply

  def extractTrainingFromXML(feed: Elem) = feed \ "Team" map Training.apply

  def extractTeamFromXML(feed: Elem) = feed \ "Team" map Team.apply

}