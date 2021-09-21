package fundamentals

fun main(args: Array<String>) {
    //1
    val x = 2
    val y = 3
    fun1(x, y)
    //2
    fun2()
    //3
    printPrime(1,20)
}

fun fun1(x: Int, y: Int) {
    println("$x + $y = ${x+y}")
}

fun fun2(){
    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    for(day in daysOfWeek) print("$day ")
    print("\n")
    for(day in daysOfWeek.filter{ it.startsWith("T") }) print("$day ")
    print("\n")
    for(day in daysOfWeek.filter { it.contains("e") }) print("$day ")
    print("\n")
    for(day in daysOfWeek.filter { it.length == 6 }) print("$day ")
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

fun printPrime(nr1 : Int, nr2: Int){
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

fun decode(word : String): (String) {
    var word1 = ""
    for(letter in word){
        word1 = "$word1${letter-1}"
    }
    return word1
}