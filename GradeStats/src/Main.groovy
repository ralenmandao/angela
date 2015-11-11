import java.io.FileNotFoundException

class Main {
	
	public static void main(String[] args) {
		println "Welcome to GradeStats!"
		Class myClass
		def command
		while (command != "exit") {
			print "> "
			command = System.in.newReader().readLine()
			command = command.toLowerCase()
			command = command.split(" ", 2)
			if (command[0] == "help") this.displayCommands()
			if (command.size() > 1 && command[0] == "load") myClass = new Class(command[1])
			if (myClass != null) {
				if (command[0] == "students") myClass.getStudents()
				if (command[0] == "assignments") myClass.getAssignments()
				if (command.size() > 1 && command[0] == "search") myClass.search(command[1])
				if (command[0] == "grades") myClass.getGrades()
				if (command.size() > 1 && command[0] == "student") myClass.getStudent(command[1])
				if (command.size() > 1 && command[0] == "assignment") myClass.getAssignment(command[1])
			}
		}
		println "\tGoodbye."
	}
	
	static displayCommands() {
		println "  Accepted commands:"
		println "\texit"
		println "\thelp"
		println "\tload [filename]"
		println "\tstudents"
		println "\tsearch [partial name]"
		println "\tassignments"
		println "\tgrades"
		println "\tstudent [student name]"
		println "\tassignment [assignment name]"
	}	
	
}
