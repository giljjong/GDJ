package quiz05_exam;

public class Student {
	
	private Exam exam;
	private String name;

	public Student(String name) {
		this.name = name;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
	
	public void info() {
		System.out.println("학생 이름 : " + name);
		exam.examInfo();
	}
}
