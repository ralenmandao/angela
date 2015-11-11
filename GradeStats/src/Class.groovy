
class Class {
	def name
	def section
	def activityTitles
	def activityPoints
	def students = []
	
	Class (def path) {
		try {
			println "  Loading..."
			def file = new File(path).text
			// NOTE: Code does not handle files with incorrect formats
			
			// each line in the txt file represents a single record			
			file = file.split(System.getProperty("line.separator"))
					
			// get activity names
			activityTitles = file[0].tokenize(",")
			activityTitles.remove(0)
			activityTitles.remove(0)
			
			// get class info + total points per activity
			activityPoints = file[1].tokenize(",")
			name = activityPoints[0]
			section = activityPoints[1]
			activityPoints.remove(0)
			activityPoints.remove(0)
			
			def currentLine;
			for (i in 2..file.size() - 1) {
				// each comma separates each field in a record
				currentLine = file[i].split(",")
				
				Student student = new Student();
				
				// get the first and last name
				student.setFirstName(currentLine[0])
				student.setLastName(currentLine[1])
				
				// set the assignments
				def assignments = [];
				for (j in 2..currentLine.size() - 1) {
					Assignment assignment = new Assignment()
					assignment.setTitle(activityTitles[j-2])
					assignment.setPossible(activityPoints[j-2])
					assignment.setPoints(currentLine[j])
					assignments.add(assignment)
					// println ("$assignment.title: $assignment.points/$assignment.possible")
				}
				student.setAssignments(assignments)
				
				students.add(student)
			}
			
			println "  Loaded class $name, section $section."
			
		} catch (FileNotFoundException ex) {
			println "  ERROR: File does not exist."
		}
	}
	
	def getStudents() {
		println "  Student Grades for $name, section $section"
		println "  Total Points Possible: ${activityPoints*.toInteger().sum()}"
		println()
		printf("  %-15s %-15s %-8s %-8s\n", "First Name", "Last Name", "Points", "Grade")
		printf("  %-15s %-15s %-8s %-8s\n", "----------", "---------", "------", "-----")
		students.each { 
			printf("  %-15s %-15s %-10s %-10s\n", it.getFirstName(), it.getLastName(), it.getPoints(), it.getGrade())
		}	
	}
	
	def getAssignments() {
		println "  Assignments for $name, section $section"
		println()
		printf("  %-15s %-10s\n", "Assignment", "Points")
		printf("  %-15s %-10s\n", "----------", "------")
		for (i in 0..activityTitles.size() - 1) {
			printf("  %-15s %-10s\n", activityTitles[i], activityPoints[i])
		}
	}
	
	def search(def name) {
		name = name.toLowerCase()
		printf("  %-15s %-15s %-8s %-8s\n", "First Name", "Last Name", "Points", "Grade")
		printf("  %-15s %-15s %-8s %-8s\n", "----------", "---------", "------", "-----")
		students.each { 
			if (it.getFirstName().toLowerCase().contains(name) || it.getLastName().toLowerCase().contains(name)) {
				printf("  %-15s %-15s %-10s %-10s\n", it.getFirstName(), it.getLastName(), it.getPoints(), it.getGrade())
			}
		}
	}
	
	def getGrades() {
		println "  Grade Breakdown for $name, section $section"
		println()
		println "  Low: ${students.average.min()}"
		println "  High: ${students.average.max()}"
		println "  Average: ${(students.sum { it.average })/students.size()}"
		println()
		println "  A: ${students.count { it.getGrade() == "A" }}"
		println "  B: ${students.count { it.getGrade() == "B" }}"
		println "  C: ${students.count { it.getGrade() == "C" }}"
		println "  D: ${students.count { it.getGrade() == "D" }}"
		println "  F: ${students.count { it.getGrade() == "F" }}"
	}
	
	def getStudent(def name) {
		name = name.toLowerCase()
	}
	
	def getAssignment(def title) {
		title = title.toLowerCase()
		activityTitles.findAll { 
			it.contains(title)
		}.each {
			int index = activityTitles.indexOf(it)
			int points = activityPoints[index].toInteger()
			printf("  %-15s: %1s points\n", it, points)
			println "  A: ${students.count { it.assignments[index] / points * 100 } }"
		}
	}
}
