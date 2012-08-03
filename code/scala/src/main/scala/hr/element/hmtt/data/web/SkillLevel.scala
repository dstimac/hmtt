package hr.element.hmtt.data.web

object Skill {
  private val levels = Map(
    0  -> "non-existant"
  , 1  -> "disastrous"
  , 2  -> "wretched"
  , 3  -> "poor"
  , 4  -> "weak"
  , 5  -> "inadequate"
  , 6  -> "passable"
  , 7  -> "solid"
  , 8  -> "excellent"
  , 9  -> "formidable"
  , 10 -> "outstanding"
  , 11 -> "brilliant"
  , 12 -> "magnificent"
  , 13 -> "world class"
  , 14 -> "supernatural"
  , 15 -> "titanic"
  , 16 -> "extra-terrestrial"
  , 17 -> "mythical"
  , 18 -> "magical"
  , 19 -> "utopian"
  , 20 -> "divine"
  )
}

sealed abstract class Skill(val level: Int) {
  val levelDesc = Skill.levels(level)
  val name = getClass.getSimpleName

  def -(skill: Skill) = level - skill.level

  override val toString =
    "%2d - %12s" format(level, levelDesc)
}

case class Stamina(override val level: Int) extends Skill(level)
case class Form(override val level: Int) extends Skill(level)
case class Scoring(override val level: Int) extends Skill(level)
case class Defending(override val level: Int) extends Skill(level)
case class Goalkeeping(override val level: Int) extends Skill(level)
case class Passing(override val level: Int) extends Skill(level)
case class Winger(override val level: Int) extends Skill(level)
case class SetPieces(override val level: Int) extends Skill(level)
case class Playmaking(override val level: Int) extends Skill(level)
case class Experience(override val level: Int) extends Skill(level)
case class Leadership(override val level: Int) extends Skill(level)


