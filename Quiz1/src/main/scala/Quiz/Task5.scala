package Quiz

class Task5 {//driver provided
  val nums = Array(0,0,0,0,0,0,0,0,0,0)
  val k = 0
  println(subarraySum(nums,k))
  //recursive function
  def subarraySum(nums: Array[Int], k: Int): Int = {
    var cnt = 0
    for(i<-nums.indices){
      verify(i,0)
    }

    def verify(index: Int,sessionTotal:Int): Unit ={
      var cur = sessionTotal
      cur += nums(index)
      if(cur == k) {
        cnt += 1
      }
      if(index + 1 != nums.size){
        verify(index + 1,cur)
      }
    }
    cnt
  }

}
