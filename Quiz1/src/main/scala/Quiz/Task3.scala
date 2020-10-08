package Quiz

class Task3 {
  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
       var value: Int = _value
       var left: TreeNode = _left
       var right: TreeNode = _right
     }
  def minDiffInBST(root: TreeNode): Int = {
    var diff = 9999999
    var h = Array[Int]()
    def minDiff(root: TreeNode){
      if (root != null) {
        h:+=root.value
        minDiff(root.left)
        minDiff(root.right)
      }
    }
    minDiff(root)
    h = h.sorted
    for(i<-0 until h.size){
      if(i + 1 != h.size){
        if(h(i + 1) - h(i) < diff){
          diff= h(i + 1) - h(i)
        }
      }
    }
    diff
  }
}
