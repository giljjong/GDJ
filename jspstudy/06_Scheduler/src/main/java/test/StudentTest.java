package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Student;
import repository.StudentDao;

public class StudentTest {

	/*
	 * 순서대로 전체 테스트 돌리기
	 * 1. 데이터 삽입 (테스터, 50, 50, 50)
	 * 2. 목록 테스트
	 * 3. 상세 테스트
	 * 4. 수정 테스트(테스터2, 60, 60, 60)
	 * 5. 삭제 테스트
	 */
	
	
	@BeforeClass
	public static void 데이터삽입테스트() {
		Student student = new Student();
		student.setName("테스터");
		student.setKor(50);
		student.setEng(50);
		student.setMath(50);
		student.setAve(50);
		student.setGrade("F");
		
		int result = StudentDao.getInstance().insertStudent(student);
		assertEquals(1, result);
		
	}
	@Test
	public void 데이터목록테스트() {
		assertEquals(6, StudentDao.getInstance().selectAllStudents().size());
	}
	@Test
	public void 데이터상세테스트() {
		assertNotNull(StudentDao.getInstance().selectStudentByNo(50));
	}
	@Test
	public void 데이터수정테스트() {
		Student student = new Student();
		student.setStuNo(6);
		student.setName("테스터2");
		student.setKor(60);
		student.setEng(60);
		student.setMath(60);
		student.setAve(60);
		student.setGrade("F");
		assertEquals(1, StudentDao.getInstance().updateStudent(student));
	}
	
	@AfterClass
	public static void 데이터삭제테스트() {
		assertEquals(1, StudentDao.getInstance().deleteStudent(6));
	}

}
