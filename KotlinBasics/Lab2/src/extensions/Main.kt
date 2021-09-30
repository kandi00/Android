package extensions

fun main(){

    //Extension functions
    //1
    "John Smith".monogram()
    //2
    val list = listOf("apple", "pear", "melon", "strawberry")
    println(list.listToString())
    //3
    print(list.longestString())

}

fun String.monogram() {
    println(split(' ').map { it[0].toUpperCase() }.joinToString(""))
}

fun List<String>.listToString() : String {
    return joinToString("#")
}

fun List<String>.longestString() : String {
    val myList = map{ it.length }
    return this[myList.indexOf(myList.max())]
}