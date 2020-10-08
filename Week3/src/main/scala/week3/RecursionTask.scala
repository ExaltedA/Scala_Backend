package week3

class RecursionTask {
  //driver for Recursion task
  val ints: List[Int] = List(1,15,6,7,8,7)
  val target = 8
  println(find(ints,target))

  //the function below analyzes if the target value exists in the given list
  def find(root: List[Int], target: Int): Boolean = {
    if (root.isEmpty){
      return false
    }
    //checks if first value is a target value
    if (root.head == target) {
      return true
    }
    //checks if it is last
    if (root.tail.isEmpty)
      return false
    find(root.tail, target)
  }
}
