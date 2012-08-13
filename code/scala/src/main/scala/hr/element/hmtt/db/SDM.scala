package hr.element.hmtt.db

import hr.element.hmtt.data.web.Player
import java.sql.PreparedStatement
import java.sql.ResultSet
import org.scribe.model.Token
import scala.collection.mutable.Map
import hr.element.hmtt.data.web.Training
import hr.element.hmtt.data.web.Team

object SDM {

  def insertPlayer(p: Player) {

    val conn = Connector.getConnection
    val sql = "INSERT INTO PLAYER(" +
      "ID, " +
      "FIRSTNAME, " +
      "LASTNAME, " +
      "AGE, " +
      "AGEDAYS, " +
      "TSI, " +
      "FORM, " +
      "STAMINA, " +
      "EXPERIENCE, " +
      "LEADERSHIP, " +
      "SALARY, " +
      "SPECIALITY, " +
      "GOALKEEPING, " +
      "PLAYMAKING, " +
      "SCORING, " +
      "PASSING, " +
      "DEFENDING, " +
      "WINGER, " +
      "SETPIECES) " +
      "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

    val pstmt = conn.prepareStatement(sql)

    pstmt.setInt(1, p.playerID)
    pstmt.setString(2, p.firstName)
    pstmt.setString(3, p.lastName)
    pstmt.setInt(4, p.age)
    pstmt.setInt(5, p.ageDays)
    pstmt.setInt(6, p.tsi)
    pstmt.setInt(7, p.playerForm.level)
    pstmt.setInt(8, p.staminaSkill.level)
    pstmt.setInt(9, p.experience.level)
    pstmt.setInt(10, p.leadership.level)
    pstmt.setInt(11, p.salary)
    pstmt.setInt(12, p.speciality.id)
    pstmt.setInt(13, p.keeperSklill.level)
    pstmt.setInt(14, p.playmakerSkill.level)
    pstmt.setInt(15, p.scorerSkill.level)
    pstmt.setInt(16, p.passingSkill.level)
    pstmt.setInt(17, p.defenderSkill.level)
    pstmt.setInt(18, p.wingerSkill.level)
    pstmt.setInt(19, p.setPiecesSkill.level)

    val a = pstmt.executeUpdate()

    conn.close()
    pstmt.close()
  }

  def updatePlayer(p: Player) {

    val conn = Connector.getConnection
    val sql = "UPDATE PLAYER " +
      "SET FIRSTNAME = ?, " +
      "LASTNAME = ?, " +
      "AGE = ?, " +
      "AGEDAYS = ?, " +
      "TSI = ?, " +
      "FORM = ?, " +
      "STAMINA = ?, " +
      "EXPERIENCE = ?, " +
      "LEADERSHIP = ?, " +
      "SALARY = ?, " +
      "SPECIALITY = ?, " +
      "GOALKEEPING = ?, " +
      "PLAYMAKING = ?, " +
      "SCORING = ?, " +
      "PASSING = ?, " +
      "DEFENDING = ?, " +
      "WINGER = ?, " +
      "SETPIECES = ? " +
      "WHERE ID = ?"

    val pstmt = conn.prepareStatement(sql)


    pstmt.setInt(19, p.playerID)
    pstmt.setString(1, p.firstName)
    pstmt.setString(2, p.lastName)
    pstmt.setInt(3, p.age)
    pstmt.setInt(4, p.ageDays)
    pstmt.setInt(5, p.tsi)
    pstmt.setInt(6, p.playerForm.level)
    pstmt.setInt(7, p.staminaSkill.level)
    pstmt.setInt(8, p.experience.level)
    pstmt.setInt(9, p.leadership.level)
    pstmt.setInt(10, p.salary)
    pstmt.setInt(11, p.speciality.id)
    pstmt.setInt(12, p.keeperSklill.level)
    pstmt.setInt(13, p.playmakerSkill.level)
    pstmt.setInt(14, p.scorerSkill.level)
    pstmt.setInt(15, p.passingSkill.level)
    pstmt.setInt(16, p.defenderSkill.level)
    pstmt.setInt(17, p.wingerSkill.level)
    pstmt.setInt(18, p.setPiecesSkill.level)

    val a = pstmt.executeUpdate()
    conn.close()
    pstmt.close()
  }

  def selectPlayer(id: Int): ResultSet = {

    val conn = Connector.getConnection
    val stmt = conn.createStatement()
    val query = "SELECT * FROM PLAYER WHERE ID = " + id
    val resultSet = stmt.executeQuery(query)

    conn.close()
    stmt.close()
    resultSet
  }

  def selectPlayerHistory (id: Int): ResultSet = {
    val conn = Connector.getConnection
    val stmt = conn.createStatement()
    val query = "SELECT * FROM PLAYERHISTORY WHERE ID = " + id
    val resultSet = stmt.executeQuery(query)

    conn.close()
    stmt.close()
    resultSet
  }

  def selectLastPlayerHistory (id: Int): ResultSet = {
    val conn = Connector.getConnection
    val stmt = conn.createStatement()
    val query = "SELECT * FROM PLAYERHISTORY WHERE ID = " + id +
          "ORDER BY TIMESTAMP DESC LIMIT 1"
    val resultSet = stmt.executeQuery(query)

    conn.close()
    stmt.close()
    resultSet
  }

  def insertToken(key: String, secret: String) {

    val conn = Connector.getConnection
    val stmt = conn.createStatement()
    val query = "INSERT INTO ACCESSTOKEN(" +
      "KEY, " +
      "SECRET)" +
      "VALUES(?,?)"

    val pstmt = conn.prepareStatement(query)
    pstmt.setString(1, key)
    pstmt.setString(2, secret)

    pstmt.executeUpdate()
    conn.close()
    stmt.close()
  }

  def deleteToken {
    val conn = Connector.getConnection
    val stmt = conn.createStatement()
    val query = "DELETE * FROM ACCESSTOKEN"

      conn.close()
    stmt.close()
  }

  def selectToken = {
    val conn = Connector.getConnection
    val stmt = conn.createStatement()
    val query = "SELECT * FROM ACCESSTOKEN"

    val resultSet = stmt.executeQuery(query)
    resultSet.next()
    conn.close()
    stmt.close()
    new Token(resultSet.getString("KEY"), resultSet.getString("SECRET"))
  }

  def selectBaseTrainingLenght = {
    val conn = Connector.getConnection
    val stmt = conn.createStatement()
    val query = "SELECT * FROM BASETRAININGLENGHT"
    val map = Map.empty[String, Double]

    val resultSet = stmt.executeQuery(query)

    while(resultSet.next()) {
      map.put(resultSet.getString("TRAININGTYPE"), resultSet.getDouble("TRAININGLENGTH"))
    }

    conn.close()
    stmt.close()
    map
  }

  def selectNextSkillFactor = {
    val conn = Connector.getConnection
    val stmt = conn.createStatement()
    val query = "SELECT * FROM NEXTSKILLFACTOR"
    val map = Map.empty[Int, Double]

    val resultSet = stmt.executeQuery(query)

    while(resultSet.next()) {
      map.put(resultSet.getInt("SKILL"), resultSet.getDouble("FACTOR"))
    }

    conn.close()
    stmt.close()
    map
  }

  def selectTrainingType(id: Int) = {
    query(
        "SELECT NAME FROM TRAININGTYPE WHERE ID = " + id
        , _.getString("NAME")
    )
  }

  def insertTraining(t: Training) = {
    val conn = Connector.getConnection
    val sql = "INSERT INTO TRAINING(" +
    "TRAININGLEVEL, " +
    "TRAININGTYPE, " +
    "STAMINAPART, " +
    "LASTTRAININGTYPE, " +
    "LASTTRAININGLEVEL, " +
    "LASTSTAMINAPART, " +
    "TRAINERID) " +
    "VALUES(?,?,?,?,?,?,?)"

    val pstmt = conn.prepareStatement(sql)

    pstmt.setInt(1, t.trainingLevel)
    pstmt.setInt(2, t.trainingType)
    pstmt.setInt(3, t.staminaTrainingPart)
    pstmt.setInt(4, t.lastTrainingTrainingType)
    pstmt.setInt(5, t.lastTrainingTrainingLevel)
    pstmt.setInt(6, t.lastTrainingStaminaTrainingPart)
    pstmt.setInt(7, t.trainer.trainerID)

    pstmt.executeUpdate()
    conn.close()
    pstmt.close()
  }

  def selectLastSkillUpdate = {
    val conn = Connector.getConnection
    val stmt = conn.createStatement()
    val query = "SELECT * FROM LASTSKILLUPDATE"


    val resultSet = stmt.executeQuery(query)
    resultSet.next()
    conn.close()
    stmt.close()
    resultSet
  }

  def insertTrainerInfo(p: Player) {
    val conn = Connector.getConnection
    val stmt = conn.createStatement()
    val query = "INSERT INTO TRAINERINFO(" +
      "TRAINERID, " +
      "TYPE," +
      "SKILL)" +
      "VALUES(?,?,?)"

    val pstmt = conn.prepareStatement(query)

    pstmt.setInt(1, p.playerID)
    pstmt.setInt(2, p.trainerData.trainerType)
    pstmt.setInt(3, p.trainerData.trainerSkill)

    pstmt.executeUpdate()
    conn.close()
    stmt.close()
  }

  def selectTrainerLevel: Int = {
    query(
        "SELECT SKILL FROM TRAINERINFO, TRAINING " +
        "WHERE " +
        "TRAINERINFO.TRAINERID = TRAINING.TRAINERID"
        , _.getInt("SKILL")
    )
  }

  def insertTeam(t: Team) {
    val conn = Connector.getConnection
    val sql = "INSERT INTO TEAM(" +
      "ID, " +
      "NAME, " +
      "ASSISTANTTRAINERS, " +
      "PSYCHOLOGISTS, " +
      "PRESSSPOKESMEN, " +
      "PHYSIOTHERAPISTS, " +
      "DOCTORS) " +
      "VALUES(?,?,?,?,?,?,?)"

    val pstmt = conn.prepareStatement(sql)

    pstmt.setInt(1, t.teamID)
    pstmt.setString(2, t.teamName)
    pstmt.setInt(3, t.assistantTrainers)
    pstmt.setInt(4, t.psychologists)
    pstmt.setInt(5, t.pressSpokesmen)
    pstmt.setInt(6, t.physiotherapists)
    pstmt.setInt(7, t.doctors)

    val a = pstmt.executeUpdate()

    conn.close()
    pstmt.close()
  }

  private def query[T](qry: String, f: ResultSet => T) = {
    val conn = Connector.getConnection
    val stmt = conn.createStatement()

    val resultSet = stmt.executeQuery(qry)
    resultSet.next()

    val res = f(resultSet)
    conn.close()
    stmt.close()
    res
  }

  def selectAssistantTrainers: Int = {
    query(
      "SELECT ASSISTANTTRAINERS FROM TEAM"
    , _.getInt("ASSISTANTTRAINERS")
    )
  }


  def selectTrainingIntensity: Int = {
    query(
      "SELECT TRAININGLEVEL FROM TRAINING"
      ,_.getInt("TRAININGLEVEL")
    )
  }

  def selectStaminaTrainingShare: Int = {
      query(
        "SELECT STAMINAPART FROM TRAINING"
        , _.getInt("STAMINAPART")
      )
  }
}