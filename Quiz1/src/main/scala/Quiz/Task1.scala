package Quiz

class Task1 {
  //Driver is not provided 90% faster

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }

  def rangeSumBST(root: TreeNode, L: Int, R: Int): Int = {
    var sum = 0

    def analyze(root: TreeNode, L: Int, R: Int): Unit = {
      if (root != null) {
        if (root.value >= L && root.value <= R) {
          sum += root.value
        }
        if (root.right != null) {
          analyze(root.right, L, R)
        }
        if (root.left != null) {
          analyze(root.left, L, R)
        }

      }
    }

    analyze(root, L, R)
    sum

  }
}
