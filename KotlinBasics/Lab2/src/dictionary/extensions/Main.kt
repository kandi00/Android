package dictionary.extensions

fun main(){

    fun String.monogram(word: String) {
        val monogram : String = ""
        word.split(' ').map { it[0].toUpperCase() }.joinToString("")
    }

    fun List<String>.longestString(){

    }


}