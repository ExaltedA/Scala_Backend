package week3

import scala.collection.mutable

class Task9 {
  //Driver
  val num = Array(1,2,2,1)
  val num2 = Array(2,2)
  println(intersection(num, num2).mkString("Array(", ", ", ")"))
  //function uses hashset, meaning(contains O(1) and stores unique elements)
  def intersection(nums1: Array[Int], nums2: Array[Int]): Array[Int] = {
    val a =mutable.HashSet[Int]()
    val outP = mutable.HashSet[Int]()
    for(i<-nums1){
        a.add(i)
    }
    for(i<- nums2){
      if(a.contains(i))
        outP.add(i)
    }
    outP.toArray
  }
}
