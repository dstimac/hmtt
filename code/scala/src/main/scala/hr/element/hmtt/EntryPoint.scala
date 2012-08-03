package hr.element.hmtt

import scala.xml.XML
import hr.element.hmtt.data.web.Player
import scala.xml.Elem
import java.io.FileOutputStream
import hr.ngs.templater.Configuration
import hr.element.hmtt.data.web.Skill
import hr.element.hmtt.oauth.Tokenizzzer
import hr.element.hmtt.oauth.Resources
import hr.element.hmtt.db._
import scalax.io.InputResource
import hr.element.hmtt.data.web.Training

object EntryPoint extends App {

  val resource     = "/players.xml"
  val newResource  = "/playersNew.xml"
  val TemplatePath = "/players_template.xlsx";
  val OutputPath   = "output/players.xlsx";


  Connector.prepareDB

  val webResource = Tokenizzzer.getResource(Resources.players)
  val feedXML2 = XML.loadString(webResource)
  val playersNew = makeXMLfromString(feedXML2)

  for(p <- playersNew) {
    SDM.insertPlayer(p)
  }

//  val resource2 = Tokenizzzer.getResource(Resources.training)
//  val feedXML = XML.loadString(resource2)
//  val training = for{e <- (feedXML \\ "Team") } yield Training(e)

  val resultSet = SDM.selectPlayer(284923815)
  while(resultSet.next()) {
    val name = resultSet.getString("FIRSTNAME") + " " + resultSet.getString("LASTNAME")
    val wage = resultSet.getInt("SALARY")
    println(name)
    println(wage)
  }

  def makeXMLfromString(feed: Elem) = for {
    e <- (feed \\ "Player")
  } yield Player(e)

  def makeReport(CDs: Seq[Player]) {
    val iS  = this.getClass().getResourceAsStream(TemplatePath)
    val fOS = new FileOutputStream(OutputPath)
    val tpl = Configuration.factory().open(iS, "xlsx", fOS)

    tpl.process(CDs.toArray)
    tpl.flush()
    fOS.close()
    iS.close()
  }

  def printReport(players: Seq[(Player, Player)]) {
    for{ (now, old)  <- players } {
      println(now.firstName + " " + old.lastName)
      println("TSI: " + now.tsi + " (" + (now.tsi - old.tsi) + ")")
      if(now.speciality.id != 0) println("Speciality: " + now.speciality)
      println("Wage: " + now.salary)

      printSkillChange(now.playerForm, old.playerForm)
      printSkillChange(now.staminaSkill, old.staminaSkill)
      printSkillChange(now.scorerSkill, old.scorerSkill)
      printSkillChange(now.passingSkill, old.passingSkill)
      printSkillChange(now.wingerSkill, old.wingerSkill)
      printSkillChange(now.playmakerSkill, old.playmakerSkill)
      printSkillChange(now.defenderSkill, old.defenderSkill)
      printSkillChange(now.setPiecesSkill, old.setPiecesSkill)
      printSkillChange(now.keeperSklill, old.keeperSklill)
      println()
     }
  }

  def printSkillChange(now: Skill, old: Skill) =
    println("%11s: %s -> %2d" format(now.getClass.getSimpleName, now, now-old))

  def printPlayers = {

    val iS = this.getClass().getResourceAsStream(resource)
    val iS2 = this.getClass().getResourceAsStream(newResource)
    val s = scala.io.Source.fromInputStream(iS).mkString
    val s2 = scala.io.Source.fromInputStream(iS2).mkString
    iS.close()
    iS2.close()

    val webResource = Tokenizzzer.getResource(Resources.players)

    val feedXML = XML.loadString(s2)
    val feedXML2 = XML.loadString(webResource)


    val playersOld = makeXMLfromString(feedXML)
    val playersNew = makeXMLfromString(feedXML2)

    val zipped = playersNew zip playersOld

    printReport(zipped)
    //makeReport(playersNew)


  }
}