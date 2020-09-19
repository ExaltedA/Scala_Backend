package week3



class Task3 {
  //driver
  val nums: Array[Int] = Array(8,1,2,2,3)
  println(smallerNumbersThanCurrent(nums))
  //simple solution
  def smallerNumbersThanCurrent(nums: Array[Int]): Array[Int] = {
    val n = nums.length
    val count = new Array[Int](102)
    val ans = new Array[Int](n)
    for (i <- 0 until n) {
      count(nums(i) + 1) += 1
    }
    for (i <- 1 until 102) {
      count(i) += count(i - 1)
    }
    for (i <- 0 until n) {
      ans(i) = count(nums(i))
    }
    ans.foreach(println(_))
    ans
  }


}
