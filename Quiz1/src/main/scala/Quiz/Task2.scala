package Quiz

class Task2 {
  val n = 4
  println(tribonacci(n))
  def tribonacci(n: Int): Int = {
    var a = Array(0,1,1)
    if(n>2){
      for(i<-3 to (n + 1)){
        a:+= (a(i - 3) + a(i - 2) + a(i - 1))
      }
    }
    a(n)
  }
}
