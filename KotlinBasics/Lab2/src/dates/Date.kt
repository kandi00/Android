package dates

data class Date(val year: Int, val month: Int, val day: Int) : Comparable<Date> {
    override fun compareTo(other: Date): Int {
        if (year == other.year) {
            if (month == other.month) {
                if (day == other.day) {
                    return 0
                } else {
                    day - other.day
                }
            } else {
                return month - other.month
            }
        }
        return year - other.year
    }
}