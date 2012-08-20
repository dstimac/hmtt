package hr.element.hmtt.data.web

import scala.xml.Node

class Training(
    val teamID                         : Int,
    val teamName                       : String,
    val trainingLevel                  : Int,
    val newTrainingLevel               : Int,
    val trainingType                   : Int,
    val staminaTrainingPart            : Int,
    val lastTrainingTrainingType       : Int,
    val lastTrainingTrainingLevel      : Int,
    val lastTrainingStaminaTrainingPart: Int,
    val trainer                        : Trainer,
    val specialTraining                : String,
    val morale                         : Int,
    val selfConfidence                 : Int,
    val experiance442                  : Int,
    val experiance433                  : Int,
    val experiance451                  : Int,
    val experiance352                  : Int,
    val experiance532                  : Int,
    val experiance343                  : Int,
    val experiance541                  : Int,
    val experiance523                  : Int,
    val experiance550                  : Int,
    val experiance253                  : Int
    ) {

}

object Training {

  def apply(elem: Node): Training = {
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

    new Training(
        teamID                          = i("TeamID"),
        teamName                        = s("TeamName"),
        trainingLevel                   = i("TrainingLevel"),
        newTrainingLevel                = tr("NewTrainingLevel"),
        trainingType                    = i("TrainingType"),
        staminaTrainingPart             = i("StaminaTrainingPart"),
        lastTrainingTrainingType        = i("LastTrainingTrainingType"),
        lastTrainingTrainingLevel       = i("LastTrainingTrainingLevel"),
        lastTrainingStaminaTrainingPart = i("LastTrainingStaminaTrainingPart"),
        trainer                         = Trainer(elem \ "Trainer"),
        specialTraining                 = s("SpecialTraining"),
        morale                          = i("Morale"),
        selfConfidence                  = i("SelfConfidence"),
        experiance442                   = i("Experience442"),
        experiance433                   = i("Experience433"),
        experiance451                   = i("Experience451"),
        experiance352                   = i("Experience352"),
        experiance532                   = i("Experience532"),
        experiance343                   = i("Experience343"),
        experiance541                   = i("Experience541"),
        experiance523                   = i("Experience523"),
        experiance550                   = i("Experience550"),
        experiance253                   = i("Experience253")
        )
  }

}