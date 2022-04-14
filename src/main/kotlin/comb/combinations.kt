package comb

import sorting.Sort
import java.math.BigInteger

fun Int.factorial(): BigInteger {
    if (this < 0) throw IllegalArgumentException("the factorial of a negative number does not exist")
    if (this == 0) return BigInteger.ONE
    var factorial = BigInteger.ONE
    for (i in 1..this) factorial = factorial.multiply(BigInteger(i.toString()))
    return factorial
}
fun A(n: Int, k: Int): BigInteger {
    if (n < 0 || k < 0 || k > n) throw IllegalArgumentException()
    return n.factorial().divide((n-k).factorial())
}
fun _A(n: Int, k: Int): BigInteger {
    if (n < 0 || k < 0) throw IllegalArgumentException()
    return BigInteger(n.toString()).pow(k)
}
fun C(n: Int, k: Int): BigInteger = A(n,k).divide(k.factorial())
fun _C(n: Int, k: Int) = C(n + k - 1, k)

fun<T: Comparable<T>> generatePermutation(array: Array<T>) {
    fun Array<T>.sortPartOfArray(from: Int, to: Int, sortMethod: (Array<T>) -> Unit = Sort.Companion::sortBubble ) {
        if (from > to || from < 0 || to < 0 || to > this.lastIndex || from > this.lastIndex) throw java.lang.IllegalArgumentException(
            "from > to"
        )
        val newArray = this.copyOfRange(from, to + 1)
        sortMethod(newArray)
        System.arraycopy(newArray, 0, this, from, to - from + 1)
    }
    fun Array<T>.swap(firstIndex: Int, secondIndex: Int) {
        val temporary = this[secondIndex]
        this[secondIndex] = this[firstIndex]
        this[firstIndex] = temporary
    }
    var iPair: Int = -1
    for (i in array.lastIndex downTo 1)
        if (array[i] > array[i-1]) {
            iPair = i - 1
            break
        }
    array.sortPartOfArray(iPair + 1, array.lastIndex)
    var j = -1
    for (i in (iPair+1)..array.lastIndex)
        if (array[i] > array[iPair]) {
            j = i
            break
        }
    array.swap(iPair, j)
}
fun <T: Comparable<T>> printAllPermutation(array: Array<T>) {
    println(array.toList())
    repeat(array.size.factorial().toInt() - 1) {
        generatePermutation(array)
        println(array.toList())
    }
}

fun generateCombinations(n: Int, k: Int): List<IntArray>? {
    val combinations: MutableList<IntArray> = ArrayList()
    val combination = IntArray(k)

    // initialize with lowest lexicographic combination
    for (i in 0 until k) {
        combination[i] = i
    }
    while (combination[k - 1] < n) {
        combinations.add(combination.clone())

        // generate next combination in lexicographic order
        var t = k - 1
        while (t != 0 && combination[t] == n - k + t) {
            t--
        }
        combination[t]++
        for (i in t + 1 until k) {
            combination[i] = combination[i - 1] + 1
        }
    }
    return combinations
}