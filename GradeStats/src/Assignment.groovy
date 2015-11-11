
class Assignment {
	def title
	def possible
	def points
	
	@Override
	public String toString() {
		return "$title: $points/$possible";
	}
	
	def getPoints() {
		return points.toInteger()
	}
	
	def getPossible() {
		return possible.toInteger()
	}
	
}
