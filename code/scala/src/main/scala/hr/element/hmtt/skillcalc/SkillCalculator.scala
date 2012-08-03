package hr.element.hmtt.skillcalc

import hr.element.hmtt.db.SDM

object SkillCalculator {

  val variable1 = 0.0404
  val BaseTrainingLength = SDM.selectBaseTrainingLenght
  val NextSkillFactor = SDM.selectNextSkillFactor


  def calcBaseAgeFactor(age: Int) = scala.math.pow(1+variable1, age - 17)

  def calcAgeFactor(age: Int, skillLevel: Int, tType: String) = {
    val factor = calcBaseAgeFactor(age) * NextSkillFactor.get(skillLevel).get *
       BaseTrainingLength.get(tType).get

  }
}