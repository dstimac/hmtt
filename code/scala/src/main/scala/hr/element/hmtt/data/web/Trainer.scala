package hr.element.hmtt.data.web

import scala.xml.NodeSeq

class Trainer(
    val trainerID: Int,
    val trainerName: String,
    val arrivalDate: String) {
}

object Trainer {
  def apply(elem: NodeSeq): Trainer = {
    new Trainer(
      trainerID = (elem \ "TrainerID").text.toInt,
      trainerName = (elem \ "TrainerName").text,
      arrivalDate = (elem \ "ArrivalDate").text
    )
  }
}