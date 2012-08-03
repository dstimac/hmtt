package hr.element.hmtt.data.web
import java.util.Date
import scala.xml.NodeSeq

class LastMatch(
  val date:               String,
  val matchID:            Int,
  val playedMinutes:      Int,
  val positionCode:       Int,
  val ratingAvarage:      Double,
  val ratingEndOfTheGame: Double
  )

object LastMatch {

  def apply(elem: NodeSeq): LastMatch = {

    new LastMatch(
      date               = (elem \ "Date").text,
      matchID            = (elem \ "MatchId").text.toInt,
      playedMinutes      = (elem \ "PlayedMinutes").text.toInt,
      positionCode       = (elem \ "PositionCode").text.toInt,
      ratingAvarage      = (elem \ "Rating").text.toDouble,
      ratingEndOfTheGame = (elem \ "RatingEndOfGame").text.toDouble
    )
  }
}