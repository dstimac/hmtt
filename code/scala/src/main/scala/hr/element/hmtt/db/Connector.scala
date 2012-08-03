package hr.element.hmtt.db

import java.io._
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

object Connector {

  val CreateTablesPath   = "src/main/resources/sql/create-tables.sql"
  val DropTablesPath     = "src/main/resources/sql/drop-tables.sql"
  val PopulateTablesPath = "src/main/resources/sql/populate-tables.sql"

  def getConnection = {
    Class.forName("org.hsqldb.jdbcDriver")
    val url = "jdbc:hsqldb:file:data/HTMTDB;shutdown=true"
    //val url = "jdbc:hsqldb:file:data/HTMTDB"

    DriverManager.getConnection(url, "sa", "")
  }

  def prepareDB {
    val conn = Connector.getConnection
    val stmt = conn.createStatement(
        ResultSet.TYPE_SCROLL_SENSITIVE,
        ResultSet.CONCUR_READ_ONLY)

    executeSqlFromFile(stmt, DropTablesPath)
    executeSqlFromFile(stmt, CreateTablesPath)
    executeSqlFromFile(stmt, PopulateTablesPath)

    stmt.close()
    conn.close()
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

}