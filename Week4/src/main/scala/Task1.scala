class Task1 {
  //driver
  val nums = Array(3,4,5,2)
  println(maxProduct(nums))
  //function
  def maxProduct(nums: Array[Int]): Int = {
    nums.sorted.slice(nums.length-2,nums.length).reduce((a,b)=>(a-1)*(b-1))
  }

}
