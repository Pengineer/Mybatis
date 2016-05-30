package hust.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import hust.bean.Student;
import hust.utils.MybatisUtil;

public class StudentDao {
	
	/**
	 * 一对一：通过学生id获取学生姓名和学生身份证号
	 * @param id
	 * @return
	 */
	public Student findById(int id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			return sqlSession.selectOne(Student.class.getName() + ".findById", id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}
	
	/**
	 * 多对一：查找班级为name的所有学生
	 * @param name
	 * @return
	 */
	public List<Student> findAllByName(String name) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			return sqlSession.selectList(Student.class.getName() + ".findAllByName", name);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}
	
	/**
	 * 多对多：查找选了课程那么的所有学生
	 * @param name
	 * @return
	 */
	public List<Student> findAllByCourseName(String name) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			return sqlSession.selectList(Student.class.getName() + ".findALlByCourseName", name);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}
	
	public static void main(String[] args) {
		StudentDao dao = new StudentDao();
		
//		Student stu = dao.findById(2014);
//		System.out.println(stu.getName() + " : " + stu.getCard().getId() + " : " + stu.getCard().getNum());
		
//		List<Student> students = dao.findAllByName("java");
//		for (Student student : students) {
//			System.out.println(student.getName() + " : " + student.getId() + " : " + student.getGrade().getLeaderName());
//		}
		
		List<Student> students = dao.findAllByCourseName("Java");
		for (Student student : students) {
			System.out.println(student.getName());
		}
	}
}
