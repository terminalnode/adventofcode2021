package xyz.terminalnode.aoc2021.solution.day18

/**
 * [[1,2],3] would be represented as:
 * SFNumber(
 *   pair = Pair(
 *     SFNumber(pair = Pair(1.toSFNumber(), 2.toSFNumber))
 *     SFNumber(literal = 3.toSFNumber()
 *   )
 * )
 */
class SFNumber(
  var pair: Pair<SFNumber, SFNumber>? = null,
  var literal: Long? = null,
  var parent: SFNumber? = null,
) {
  operator fun plus(other: SFNumber): SFNumber {
    val newParent = SFNumber(Pair(this, other))
    this.parent = newParent
    other.parent = newParent
    return newParent
  }

  private fun createLiteral(literal: Long) = SFNumber(literal = literal, parent = this)

  fun findExplodingChild(): SFNumber? =
    if (shouldExplode()) this
    else if (pair == null) null
    else leftChild.findExplodingChild() ?: rightChild.findExplodingChild()

  fun findSplittingChild(): SFNumber? =
    if (shouldSplit()) this
    else if (pair == null) null
    else leftChild.findSplittingChild() ?: rightChild.findSplittingChild()

  val leftChild: SFNumber get() = pair!!.first
  val rightChild: SFNumber get() = pair!!.second
  val magnitude: Long get() = if (literal != null) {
    literal!!
  } else {
    (leftChild.magnitude * 3) + (rightChild.magnitude * 2)
  }

  private fun getLeftFromChild(): SFNumber {
    if (literal != null) return this
    var leftChild = pair?.first ?: throw IllegalStateException("Trying to get left child from literal $this")
    while (!leftChild.isLiteral()) leftChild = leftChild.getLeftFromChild()
    return leftChild
  }

  private fun getRightFromChild(): SFNumber {
    if (literal != null) return this
    var rightChild = pair?.second ?: throw IllegalStateException("Trying to get left child from literal $this")
    while (!rightChild.isLiteral()) rightChild = rightChild.getRightFromChild()
    return rightChild
  }

  private fun getLeftFromParent(): SFNumber? {
    if (literal != null) return this

    val parentLeft = parent?.pair?.first
    return if (parentLeft !== this) parentLeft
    else parent?.getLeftFromParent()
  }

  private fun isLiteral() = literal != null

  private fun getRightFromParent(): SFNumber? {
    if (literal != null) return this

    val parentRight = parent?.pair?.second
    return if (parentRight !== this) parentRight
    else parent?.getRightFromParent()
  }

  private fun shouldExplode(): Boolean {
    if (pair == null) return false
    if (parent?.parent?.parent?.parent == null) return false
    return true
  }

  fun explode() {
    val left = getLeftFromParent()
    left?.let {
      val literal = if (it.isLiteral()) it else it.getRightFromChild()
      val thisLeft = pair?.first?.literal ?: throw IllegalStateException("Missing left pair in $this")
      literal.literal = literal.literal!! + thisLeft
    }

    val right = getRightFromParent()
    right?.let {
      val literal = if (it.isLiteral()) it else it.getLeftFromChild()
      val thisRight = pair?.second?.literal ?: throw IllegalStateException("Missing right pair in $this")
      literal.literal = literal.literal!! + thisRight
    }

    pair = null
    literal = 0
  }

  private fun shouldSplit() = literal?.let { it >= 10 } ?: false

  fun split(): SFNumber {
    val number = literal ?: throw IllegalStateException("Trying to split SFNumber with no literal value")
    if (number < 10) throw IllegalStateException("Trying to split SFNumber with literal value less than 10 ($number)")
    literal = null

    val left = createLiteral(number / 2)
    val right = createLiteral(if (number % 2 == 0L) number / 2 else number / 2 + 1)
    pair = Pair(left, right)

    return this
  }

  override fun toString(): String {
    val pair = pair
    val s = if (pair != null) {
      "[${pair.first},${pair.second}]"
    } else {
      return literal.toString()
    }

    // Mark orphans with asterisk
    // Should ideally only be the outermost number
    return if (parent == null) "*$s" else s
  }
}