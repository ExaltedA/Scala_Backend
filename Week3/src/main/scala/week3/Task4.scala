package week3

class Task4 {
  //Driver
  val A: Array[Int] = Array(1,3,2,3)
  println(repeatedNTimes(A))
  //function itself
  def repeatedNTimes(A: Array[Int]): Int = {
    //sort the array
    val arr = A.sorted
    //if there are any non unique numbers at first n+1 elements(sorted)
    //return the element
    for(i<-0 until( arr.length/2+1)) {
      if(arr(i) == arr(i+1)){
        return arr(i)

      }
    }
    0
  }

}
