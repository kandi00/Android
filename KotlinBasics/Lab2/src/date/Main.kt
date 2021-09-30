package date

import kotlin.random.Random

fun main(){

    //Cheking extesion functions
    val date = Date(2000, 10, 1)
    println(date)
    println("Is it leap year? ${date.leapYear()}")
    println("Is valid? ${date.validDate()}")
    println()

    //Generating dates and placing them
    val listOfValidDates = mutableListOf<Date>()
    var valid = 0
    println("Invalid dates: ")
    while(true) {
        val randomDate = Date(Random.nextInt(1000, 2030), Random.nextInt(1, 18), Random.nextInt(1, 34))
        if(randomDate.validDate()) {
            valid++
            listOfValidDates.add(randomDate)
        } else {
            println(randomDate)
        }
        if(valid==10) break
    }

    println("\nValid dates: ")
    listOfValidDates.forEach{ println(it) }

    println("\nSorted list - natural order")
    listOfValidDates.sort()
    listOfValidDates.reverse()
    listOfValidDates.forEach{ println(it) }

    println("\nSorted list - using comparator")
    val dateComparator = Comparator { date1: Date, date2: Date -> date1.day - date2.day }
    listOfValidDates.sortedWith(dateComparator).forEach { println(it) }
}

fun Date.leapYear() = (year % 400 == 0 || year % 4 == 0 && year % 100 != 0)

fun Date.validDate() : Boolean{
    if (year < 0 || month !in 1..12 || day !in 0..31) {
        return false
    }

    if (leapYear()) {
        if (month == 2 && day > 29) {
            return false
        }
    } else {
        if (month == 2) {
            if (day > 28) {
                return false
            }
        }
        if (month == 2 || month == 4 || month == 6 || month == 9 || month == 11) {
            if (day > 30) {
                return false
            }
        }
    }
    return true
}