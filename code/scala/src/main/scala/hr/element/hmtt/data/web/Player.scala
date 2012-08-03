package hr.element.hmtt.data.web

import java.util.Date
import scala.xml.Node

class Player (
  val playerID:         Int,
  val firstName:        String,
  val nickName:         String,
  val lastName:         String,
  val playerNumber:     Int,
  val age:              Int,
  val ageDays:          Int,
  val tsi:              Int,
  val playerForm:       Form,
  val statement:        String,
  val experience:       Experience,
  val loyality:         Int,
  val motherClubBonus:  Boolean,
  val leadership:       Leadership,
  val salary:           Int,
  val isAbroad:         Boolean,
  val agreeability:     Int,
  val aggressivness:    Int,
  val honesty:          Int,
  val leaugeGoals:      Int,
  val cupGoals:         Int,
  val frendliesGoals:   Int,
  val careerGoals:      Int,
  val careerHattricks:  Int,
  val speciality:       Speciality,
  val transferListed:   Boolean,
  val nationalTeamID:   Int,
  val countryID:        Int,
  val caps:             Int,
  val capsU20:          Int,
  val cards:            Int,
  val injuryLevel:      Int,
  val staminaSkill:     Stamina,
  val keeperSklill:     Goalkeeping,
  val playmakerSkill:   Playmaking,
  val scorerSkill:      Scoring,
  val passingSkill:     Passing,
  val wingerSkill:      Winger,
  val defenderSkill:    Defending,
  val setPiecesSkill:   SetPieces,
  val trainerData:      TrainerData,
  val playerCategoryID: Int,
  val lastMatch:        LastMatch
  ) {

  override def toString = firstName + " " + lastName
}

object Player {

  def apply(elem: Node): Player = {
    def s(tag: String) = (elem \ tag).text
    def i(tag: String) = s(tag).toInt
    def b(tag: String) = s(tag).toBoolean

    new Player(
      playerID         = i("PlayerID"),
      firstName        = s("FirstName"),
      nickName         = s("NickName"),
      lastName         = s("LastName"),
      playerNumber     = i("PlayerNumber"),
      playerCategoryID = i("PlayerCategoryId"),
      age              = i("Age"),
      ageDays          = i("AgeDays"),
      tsi              = i("TSI"),
      playerForm       = Form(i("PlayerForm")),
      experience       = Experience(i("Experience")),
      loyality         = i("Loyalty"),
      motherClubBonus  = b("MotherClubBonus"),
      leadership       = Leadership(i("Leadership")),
      salary           = i("Salary"),
      isAbroad         = false,
      agreeability     = i("Agreeability"),
      aggressivness    = i("Aggressiveness"),
      honesty          = i("Honesty"),
      leaugeGoals      = i("LeagueGoals"),
      cupGoals         = i("CupGoals"),
      frendliesGoals   = i("FriendliesGoals"),
      careerGoals      = i("CareerGoals"),
      careerHattricks  = i("CareerHattricks"),
      cards            = i("Cards"),
      injuryLevel      = i("InjuryLevel"),
      statement        = s("Statement"),
      speciality       = new Speciality(i("Specialty")),
      transferListed   = false,
      nationalTeamID   = i("NationalTeamID"),
      countryID        = i("CountryID"),
      caps             = i("Caps"),
      capsU20          = i("CapsU20"),
      staminaSkill     = Stamina(i("StaminaSkill")),
      keeperSklill     = Goalkeeping(i("KeeperSkill")),
      playmakerSkill   = Playmaking(i("PlaymakerSkill")),
      scorerSkill      = Scoring(i("ScorerSkill")),
      passingSkill     = Passing(i("PassingSkill")),
      wingerSkill      = Winger(i("WingerSkill")),
      defenderSkill    = Defending(i("DefenderSkill")),
      setPiecesSkill   = SetPieces(i("SetPiecesSkill")),
      trainerData      = TrainerData(elem \ "TrainerData"),
      lastMatch        = LastMatch(elem \ "LastMatch")
      )
  }
}