class Task3 {
  //Driver
  val day = 31
  val month = 8
  val year = 2019
  println(dayOfTheWeek(day,month,year))
  /*
  Algorithm was wrapped into case class as it was required in the assignment requirements
  In this algorithm January and February are counted as months 13 and 14 of the previous year.
  While calculating days, the leap year factor is also calculated.
  For the calculated number of months, this days value is calculated.
  Then, the mod value of the "days" maps to the corresponding day in the DAYS.
   */
  case class DayCalculate(day: Int, month: Int, year: Int) {
    val d: Int = (14 - month) / 12
    val y: Int = year - d
    val m: Int = month + 12 * d - 2
    val days: Int = (day + y + (y / 4) + (y / 400) - (y / 100) + (31 * m) / 12) % 7
  }

  def dayOfTheWeek(day: Int, month: Int, year: Int): String = {

    val DAYS: Array[String] = Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
    DAYS(DayCalculate(day,month,year).days)

  }
}
