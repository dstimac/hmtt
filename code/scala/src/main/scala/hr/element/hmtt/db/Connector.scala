package hr.element.hmtt.db

import java.io._
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

object Connector {

  val FS = System.getProperty("file.separator")

  val CreateTablesPath   = "src" + FS + "main" + FS + "resources" + FS + "sql" + FS + "create-tables.sql"
  val DropTablesPath     = "src" + FS + "main" + FS + "resources" + FS + "sql" + FS + "drop-tables.sql"
  val PopulateTablesPath = "src" + FS + "main" + FS + "resources" + FS + "sql" + FS + "populate-tables.sql"
  val CreateTriggersPath = "src" + FS + "main" + FS + "resources" + FS + "sql" + FS + "create-triggers.sql"

  def getConnection = {
    Class.forName("org.hsqldb.jdbcDriver")
    val url = "jdbc:hsqldb:file:data/HTMTDB;shutdown=true;write_delay=false"
//    val url = "jdbc:hsqldb:file:data/HTMTDB"
//    val url = "jdbc:hsqldb:file:data/HTMTDB;write_delay=false"

    DriverManager.getConnection(url, "sa", "")
  }

  def prepareDB {
    val conn = Connector.getConnection
    val stmt = conn.createStatement(
        ResultSet.TYPE_SCROLL_SENSITIVE,
        ResultSet.CONCUR_READ_ONLY)

    try {
    executeSqlFromFile(stmt, DropTablesPath)
    executeSqlFromFile(stmt, CreateTablesPath)
    executeSqlFromFile(stmt, PopulateTablesPath)
    executeFullSqlFromFile(stmt, CreateTriggersPath)
    } catch {
      case e: Exception => e.printStackTrace
    } finally  {
      stmt.close()
      conn.close()
    }

  }

  def executeSqlFromFile(stmt: Statement, filePath: String) = {
    val file = new FileInputStream(filePath)
    val input = new BufferedReader(new InputStreamReader(file))
    var sqlStmt = new StringBuilder()
    var line: String = ""

    line = input readLine()
    while ( line != null){
      //println(line);
      sqlStmt append(line)
      if (line.contains(';')) {
        //println("Executing statement: " + sqlStmt.toString());
        stmt.executeUpdate(sqlStmt.toString())
        sqlStmt = new StringBuilder()
      }
      line = input readLine()
    }

    file.close()
  }

  def executeFullSqlFromFile(stmt: Statement, filePath: String) = {
    val src1 = scala.io.Source.fromFile(filePath)("UTF-8")
    val sql = src1.mkString
    src1.close()

    stmt.executeUpdate(sql)

  }

}