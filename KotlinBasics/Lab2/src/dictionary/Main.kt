package dictionary

import dictionary.types.ListDictionary
import dictionary.types.TreeSetDictionary
import dictionary.utility.DictionaryProvider

fun main(){

    val dict: IDictionary = TreeSetDictionary
    //val dict: IDictionary = ListDictionary
    //val dict: IDictionary = DictionaryProvider.createDictionary(DictionaryType.HASH_SET)
    println("Number of words: ${dict.size()}")
    var word: String?
    while(true){
        print("What to find? ")
        word = readLine()
        if( word.equals("quit")){
            break
        }
        println("Result: ${word?.let { dict.find(it) }}")
    }
}