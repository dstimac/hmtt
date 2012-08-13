package hr.element.hmtt.data.web

import scala.xml.NodeSeq

class TrainerData(
  val trainerType: Int,
  val trainerSkill: Int) {
}

object TrainerData {


  def apply(elem: NodeSeq): TrainerData = {
    def s(tag: String) = (elem \ tag).text
    def i(tag: String) = s(tag).toInt
    def tr(tag: String) = {
      if (s(tag) == "") {
        -1
      }
      else {
        s(tag).toInt
      }
    }

    new TrainerData(
    trainerType  = tr("TrainerType"),
    trainerSkill = tr("TrainerSkill")
    )
  }
}