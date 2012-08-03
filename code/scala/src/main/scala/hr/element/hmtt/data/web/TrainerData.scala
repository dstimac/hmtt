package hr.element.hmtt.data.web

import scala.xml.NodeSeq

class TrainerData(
  val trainerType: Int,
  val trainerSkill: Int) {
}

object TrainerData {
  def apply(elem: NodeSeq): TrainerData = {
    new TrainerData(
//    trainerType = (elem \ "TrainerType").text.toInt,
//    trainerSkill = (elem \ "TrainerSkill").text.toInt
      trainerType = -1,
      trainerSkill = -1
    )
  }
}