package week3

class Task10 {
  //Driver
  val n = 3
  val target: Array[Int] = Array(1,3)
  val target2: Array[Int] = Array(1,2,3)
  // to test second example replace target with target2
  println(buildArray(target,n))

  //simple function that iterates through the array, and checks if we should pop or not
  def buildArray(target: Array[Int], n: Int): List[String] = {
    var outP: List[String] = List()
    var num=1;
    var i=0;
    while(num<=n && i<target.length){
      outP= outP :+ "Push"
      if (target(i)!=num){
        outP= outP :+ "Pop"
        num+=1
      }
      else{
        num+=1
        i+=1
      }
    }
    outP
  }

}
