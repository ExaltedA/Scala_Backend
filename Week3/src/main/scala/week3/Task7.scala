package week3
//TODO:faster than 72% and uses less than 85.71%
class Task7 {
//driver
  val mat = Array(
    Array(1, 1, 0, 0, 0),
    Array(1, 1, 1, 1, 0),
    Array(1, 0, 0, 0, 0),
    Array(1, 1, 0, 0, 0),
    Array(1, 1, 1, 1, 1))
  val k = 3
  println(kWeakestRows(mat, k).mkString("Array(", ", ", ")"))
  /*Function counts 1's and then maps(sorted) by count with Array of indexes
  as value so if there are several equal counts doesn't mess up

  */
  def kWeakestRows(mat: Array[Array[Int]], k: Int): Array[Int] = {
    import scala.collection.mutable   //So leetCode can import as well
    var outP:Array[Int] = Array()
    var mapS: mutable.SortedMap[Int, Array[Int]] = mutable.SortedMap()
    for(i<- mat.indices) {
    {
      var tmp = 0
      for(j<- mat(0).indices){
        if(mat(i)(j) == 1)
          tmp+=1
      }
      if(mapS.contains(tmp)){
        mapS = mapS.clone.addOne(tmp, mapS(tmp) :+ i)
      }
      else mapS.addOne(tmp-> Array(i) )
    }
    }
    for(i<- mapS.values){
      outP = outP ++ i
    }
    outP.slice(0,k)
  }
}