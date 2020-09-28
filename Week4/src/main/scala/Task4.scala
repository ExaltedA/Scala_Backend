

class Task4 {
//driver
  val nums = Array(1,3,1,5,4)
  val k = 0
  println(findPairs(nums,k))
  //pure fuckntion
  def findPairs(nums: Array[Int], k: Int): Int = {
    import scala.collection.mutable
    val cnt = mutable.HashMap[Int,Int]()
    for (x <- nums) {
      cnt.put(x, cnt.getOrElse(x, 0) + 1)
    }
    var res = 0
    for (x <- cnt.keySet) {
      if ((k > 0 && cnt.contains(x + k)) || (k == 0 && cnt.get(x).get > 1)) {
        res+=1
      }
    }
    res
  }

}
