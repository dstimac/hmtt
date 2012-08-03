package hr.element.hmtt.db

import java.sql.ResultSet

case class ResultSetPlayersData(
    val players        : ResultSet,
    val skillLevels    : ResultSet,
    val specialtites   : ResultSet) {

}