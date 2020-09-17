package week3

/*Task 2
[Faster than 100% of submissions XD]

*/
class Task2 {//given structure
  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
  }//Inputs
  var head: ListNode = new ListNode(1, new ListNode(0,new ListNode(1)))
  //driver
  var dec = getDecimalValue(head)
  println(dec)
  //main function
  def getDecimalValue(head: ListNode): Int = {
//fields
    var num,temp,rem,dec:BigInt = 0
    var b = 1
    var numStr: String = ""
    //recursive funciton for list data retrieval
    def getNum(head: ListNode): BigInt ={


      numStr += head.x

      if(head.next != null)
        getNum(head.next)
      else
        BigInt(numStr)

    }
    num = getNum(head);
    temp = num;

    while (temp > 0)
    {

      rem = temp % 10;
      dec = dec + rem * b;
      b *= 2;
      temp /= 10;

    }
    dec.toInt
  }
}
