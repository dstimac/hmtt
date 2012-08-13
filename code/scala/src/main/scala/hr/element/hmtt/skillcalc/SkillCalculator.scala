package hr.element.hmtt.skillcalc

import hr.element.hmtt.db.SDM
import scala.math._

object SkillCalculator {

  val variable1 = 0.0404
  val variable2 = 0.0910
  lazy val factor1 = 1 + (7-TrainerSkill)*variable2
  lazy val factor2 = 1 + (log10(11) - log10(AssistantTrainers+1)) * 0.2749
  lazy val factor3 = 100.0/TrainingIntensity
  lazy val factor4 = 1 - StaminaTrainingShare/100.0

  lazy val AssistantTrainers = SDM.selectAssistantTrainersFromTeam
  lazy val TrainerSkill = min(SDM.selectTrainerLevel, 7.5)
  lazy val BaseTrainingLength = SDM.selectBaseTrainingLenght
  lazy val NextSkillFactor = SDM.selectNextSkillFactor
  lazy val TrainingIntensity = SDM.selectTrainingIntensity
  lazy val StaminaTrainingShare = SDM.selectStaminaTrainingShare

  def calcBaseAgeFactor(age: Int) = scala.math.pow(1+variable1, age - 17)

  def calcTrainingLenght(age: Int, skillLevel: Int, tType: String) = {
    val factor = NextSkillFactor.get(skillLevel).get * calcBaseAgeFactor(age) *
       BaseTrainingLength.get(tType).get * factor1 * factor2 * factor3 *
       1/factor4
     factor
  }
}