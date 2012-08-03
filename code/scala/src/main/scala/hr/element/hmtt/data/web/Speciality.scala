package hr.element.hmtt.data.web

class Speciality(val id: Int) {

  override def toString =
    id match {
      case 0  => ""
      case 1  => "Tehnical"
      case 2  => "Quick"
      case 3  => "Powerfull"
      case 4  => "Unpredictable"
      case 5  => "Head specialist"
      case 6  => "Regainer"
      case _  => ""
      }
}