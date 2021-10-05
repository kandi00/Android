package dictionary.utility

import dictionary.DictionaryType
import dictionary.IDictionary
import dictionary.type.HashSetDictionary
import dictionary.type.ListDictionary
import dictionary.type.TreeSetDictionary

class DictionaryProvider {

    companion object {
        fun createDictionary(type: DictionaryType): IDictionary {
            return when (type) {
                DictionaryType.ARRAY_LIST -> ListDictionary
                DictionaryType.TREE_SET -> TreeSetDictionary
                DictionaryType.HASH_SET -> HashSetDictionary
            }
        }
    }

}