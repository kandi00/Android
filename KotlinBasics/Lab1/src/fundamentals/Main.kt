package fundamentals

import kotlin.random.Random

fun main() {

    //1
    val x = 2
    val y = 3
    fun1(x, y)

    //2
    var daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    fun2(daysOfWeek)

    //3
    printPrimesWithinARange(1,20)
    print("\n")

    //4
    var word = "abc"
    word = messageCoding(word) {encode(it)}
    println(word)
    //word = messageCoding(word, ::encode)

    word = messageCoding(word) {decode(it)}
    println(word)
    //5

    //Compact function = single-expression function
    printEvenNumbers(listOf(1,2,3,4,5,6))
    print("\n")

    //6
    fun6(listOf(1,2,3,4,5,6),daysOfWeek)

    //7
    daysOfWeek = daysOfWeek.toMutableList()
    daysOfWeek.removeAll { it.contains("n") }
    daysOfWeek.forEach{ print("$it ")}
    print("\n")
    daysOfWeek.withIndex().forEach{ println("Item at ${it.index} is ${it.value}") }
    daysOfWeek.sortedBy { it }.forEach{ print("$it ") }
    print("\n")

    //8
    val array  = List(10) { Random.nextInt(0, 100) }
    array.forEach{ println("$it ")}
    array.sortedBy { it }
    if (array.filter { it % 2 == 0 }.isNotEmpty()) println("The array contains at least one even number!")
    if (array.filter { it % 2 == 0 }.size == array.size) println("All the numbers are even!")
    var sum = 0.0
    array.forEach { sum+=it}
    println("Average: ${sum/array.size}")

}

fun fun1(x: Int, y: Int) {
    println("$x + $y = ${x+y}")
}

fun fun2(daysOfWeek : List<String>){
    for(day in daysOfWeek) print("$day ")
    print("\n")
    for(day in daysOfWeek.filter{ it.startsWith("T") }) print("$day ")
    print("\n")
    for(day in daysOfWeek.filter { it.contains("e") }) print("$day ")
    print("\n")
    for(day in daysOfWeek.filter { it.length == 6 }) print("$day ")
    print("\n")
}

fun isPrime(number : Int): Boolean {
    var flag = true
    if( number % 2 == 0 && number != 2) return false
    for( i in 3..kotlin.math.sqrt(number.toDouble()).toInt() step 2){
        if(number%i==0) {
            flag = false
            break
        }
    }
    return flag
}

fun printPrimesWithinARange(nr1 : Int, nr2: Int){
    for(i in nr1..nr2) if( isPrime(i)) print("$i ")
}

fun messageCoding(msg: String, func: (String) -> String): String {
    return func(msg)
}

fun encode(word : String): String {
    var word1 = ""
    for(letter in word){
        word1 = "$word1${letter+1}"
    }
    return word1
}

fun decode(word : String): String {
    var word1 = ""
    for(letter in word){
        word1 = "$word1${letter-1}"
    }
    return word1
}

fun printEvenNumbers(list : List<Int>) = list.filter { it % 2 == 0 }.forEach{print("$it ")}

fun fun6(list : List<Int>, daysOfWeek : List<String>){
    list.map { it * 2 }.forEach{ print("$it ") }
    print("\n")
    daysOfWeek.map { it.toUpperCase() }.forEach{ print("$it ") }
    print("\n")
    daysOfWeek.map { it[0].toLowerCase() }.forEach{ print("$it ") }
    print("\n")
    var sum = 0
    daysOfWeek.forEach{ print("${it.length} "); sum += it.length }
    print("\n")
    print("The average length of days = ${sum/7.0}")
    print("\n")
}