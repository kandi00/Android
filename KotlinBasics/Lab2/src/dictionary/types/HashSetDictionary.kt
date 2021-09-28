package dictionary.types

import dictionary.IDictionary
import java.io.File

object HashSetDictionary : IDictionary {

    val words = hashSetOf<String>()

    init{
        File(IDictionary.fileName).readLines().forEach { HashSetDictionary.add(it) }
    }

    override fun add(word: String): Boolean {
        if(!HashSetDictionary.find(word)){
            HashSetDictionary.words.add(word)
            return true
        }
        return false
    }

    override fun find(word: String): Boolean {
        return words.contains(word)
    }

    override fun size(): Int {
        return words.size
    }
}