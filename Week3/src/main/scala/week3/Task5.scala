package week3

class Task5 {
  //Driver
  val nums: Array[Int] = Array(43,39)
  println(decompressRLElist(nums).mkString("Array(", ", ", ")"))
  //function that adds onto string and then splits and converts into int array
    def decompressRLElist(nums: Array[Int]): Array[Int] = {
      var outp = ""
      for(i<-nums.indices){
        if(i%2==0) {
          outp += ((nums(i+1).toString + ",")* nums(i))
        }
      }
      outp.dropRight(1).split(',').flatMap(_.toIntOption)
    }


}
