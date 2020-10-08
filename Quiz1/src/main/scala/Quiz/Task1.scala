package Quiz

class Task1 {

  //Driver is not provided 90% faster

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }

  def rangeSumBST(root: TreeNode, L: Int, R: Int): Int = {


    def analyze(root: Option[TreeNode], L: Int, R: Int, cnt:Int): Int = {
      var nSum = cnt
      root match {
        case Some(node) => {
          if (node.value >= L && node.value <= R) {
            nSum += node.value
          }
          nSum += analyze(Some(node.right), L, R, nSum)
          nSum += analyze(Some(node.left), L, R, nSum)
          nSum
        }
        case None => {
          0
        }
      }}
    analyze(Option(root), L, R, 0)

  }
}
