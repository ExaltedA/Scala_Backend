package week3

class Task1 {
  //LCode task 1
  //No Driver Provided
  def kidsWithCandies(candies: Array[Int], extraCandies: Int): Array[Boolean] = {
    var max = 0
    //checks for the max before adding extras
    for(i <- candies.indices){
      if(max<candies(i)){
        max = candies(i)
      }
      candies(i) +=extraCandies
    }//filling bool array with falses
    val isGreat: Array[Boolean] = Array.fill(candies.length){false}
    //if candies + extras greater than the former max then assign true
    for(i <- candies.indices){
      if(candies(i) >= max)
        isGreat(i) = true
    }
    isGreat
  }
}
