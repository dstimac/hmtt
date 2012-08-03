package hr.element.hmtt.db

import java.io._
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class PlayerData {

  val filePath = "src/main/resources/sql/create-tables.sql"

  def getResultSet = {
    val conn = Connector.getConnection
    val stmt = conn.createStatement(
        ResultSet.TYPE_SCROLL_SENSITIVE,
        ResultSet.CONCUR_READ_ONLY)

    Connector.executeSqlFromFile(stmt, filePath)

    val players      = stmt.executeQuery("SELECT * FROM PLAYER")
    val skillLevels  = stmt.executeQuery("SELECT * FROM SKILLLEVEL")
    val specialities = stmt.executeQuery("SELECT * FROM SPECIALITY")

    stmt.close()
    conn.close()
    new ResultSetPlayersData(players, skillLevels, specialities)
  }

}