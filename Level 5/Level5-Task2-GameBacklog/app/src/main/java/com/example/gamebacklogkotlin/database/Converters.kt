package com.example.gamebacklogkotlin.database

import androidx.room.TypeConverter
import org.threeten.bp.LocalDate

class Converters {

	@TypeConverter
	fun fromTimestamp(dateString: String?): LocalDate? {
		return if(dateString == null) {
			null
		} else {
			LocalDate.parse(dateString)
		}
	}
	
	@TypeConverter
	fun dateToTimestamp(date: LocalDate?): String? {
		return if(date == null) {
			null
		} else {
			date.toString()
		}
	}
}