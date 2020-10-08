package Quiz

class Task4 {class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
     var value: Int = _value
     var left: TreeNode = _left
     var right: TreeNode = _right
   }
  def longestUnivaluePath(root: TreeNode): Int = {
    var len = 0
    def analyze(root: TreeNode, prev: Int): Int = {

      if (root != null) {
        var left = analyze(root.left, root.value)
        var right = analyze(root.right, root.value)

        len = len.max(right + left)
        if(root.value == prev){
          left.max(right) + 1
        }
        else 0}
      0
    }

    analyze(root,0)
    len
  }
}
