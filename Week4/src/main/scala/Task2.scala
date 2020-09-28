class Task2 {
  //driver
  val salary = Array(48000,59000,99000,13000,78000,45000,31000,17000,39000,37000,93000,77000,33000,
    28000,4000,54000,67000,6000,1000,11000)
  println(average(salary))
  //pure function
  def average(salary: Array[Int]): Double = {
    val double:Double = salary.sorted.slice(1,salary.length-1).sum
    double/(salary.length-2)
  }
}
