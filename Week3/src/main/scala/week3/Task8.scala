package week3

import scala.util.control.Breaks._


//TODO:75% faster, 75% memory less
class Task8 {
  //TODO:No driver provided given that there are no implementation of CustomFunction
  var v:Vector[Array[Int]] = Vector[Array[Int]]()
  var a:List[List[Int]] = List[List[Int]]()

  def findSolution(customfunction: CustomFunction, z: Int): List[List[Int]] = {
    //import so that breakable is available
    import scala.util.control.Breaks._
    //locals
    var outList:List[List[Int]] = List[List[Int]]()
    /*if combination is greater then function out of valid
     range so break and continue outer loop
    if we find combination then push i,j as list onto List of lists
    * */
    for(i <- 1 to z ){
      breakable{
        for (j <- 1 to z){
          val suspect: Int = customfunction.f(i, j)
          if(suspect>z){
            break
          }
          else if(suspect == z){
            var list: List[Int] = List(i,j)
            a= a :+ list
          }
        }
      }}
    a
  }

}

abstract case class CustomFunction(){
  def f(x:Int, y:Int) :Int {}
}



