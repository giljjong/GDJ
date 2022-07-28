package ex09_this;

public class School {

	// 필드
	private Student[] students;
	private int idx;		// student 배열의 인덱스, student 배열에 저장된 학생 숫자
	
	// 생성자
	public School(int cnt) {
		students = new Student[cnt];
	}
	
	// 메소드
	public void addStudent(Student student) {
		if(students.length == idx) {
			System.out.println("더이상 추가할 수 없습니다.");
			return;
		} 
		this.students[idx++] = student;
	}
	
	public void printStudents(){
		/*
		for(int i = 0; i < idx; i++) {
			System.out.println(students[i].getName() + ", " + students[i].getStuNo());
		}
		*/
		
		for(Student student : students) {
			if(student != null) {
			System.out.println(student.getName() + " , " + student.getStuNo());
			}
		}
	}
	
}
