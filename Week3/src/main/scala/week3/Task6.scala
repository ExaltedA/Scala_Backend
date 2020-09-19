package week3

class Task6 {
  val n = 5
  println(sumZero(n).mkString("Array(", ", ", ")"))

  def sumZero(n: Int): Array[Int] = {
    val b:Array[Int]=new Array[Int](n)
    if(n==1)
    {
      b(0)=0
    }
    else if(n%2!=0){
      b(0)=0
      for(i<-1 to (n-1) by 2){
        b(i)=i
        b(i+1)=(-1)*i
      }
    }
    else if(n%2==0){
      for(i<-0 to (n-1) by 2){
        b(i)=i+1
        b(i+1)= -(i+1)
      }
    }
    b
  }
}
