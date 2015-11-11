class Student {
	def firstName
	def lastName
	def average
	def assignments = []
	
	def getPoints() {
		assignments.sum { it.points }
	}
	
	def getAverage() {
		getPoints() / (assignments.sum { it.possible } ) * 100
	}
	
	def getGrade() {
		def ave = getAverage()
		if ( ave >= 90) "A"
		else if (ave >= 80) "B"
		else if (ave >= 70) "C"
		else if (ave >= 60) "D"
		else 
			"F"
	}
}
