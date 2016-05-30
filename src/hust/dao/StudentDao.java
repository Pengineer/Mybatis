package hust.dao;

import org.apache.ibatis.session.SqlSession;

import hust.bean.Student;
import hust.utils.MybatisUtil;

public class StudentDao {
	
	/**
	 * 通过学生id获取学生姓名和学生身份证号
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
	
	
	public static void main(String[] args) {
		StudentDao dao = new StudentDao();
		Student stu = dao.findById(2014);
		System.out.println(stu.getName() + " : " + stu.getCard().getId() + " : " + stu.getCard().getNum());
	}
}
