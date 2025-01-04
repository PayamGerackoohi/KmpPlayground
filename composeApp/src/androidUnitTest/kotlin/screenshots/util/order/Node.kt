package screenshots.util.order

abstract class Node {
    private var index = 0
    private var order: Int = 0
    private var name: String = ""

    constructor()

    constructor(title: String, parent: Node) {
        order = parent.nextIndex()
        name = parent.merge("$order.$title")
    }

    private fun merge(childName: String) = if (name.isEmpty()) childName else "${name}_$childName"

    private fun nextIndex() = index++

    class Leaf(title: String, parent: Node) {
        var name = parent.merge("${parent.nextIndex()}.$title")
    }
}
