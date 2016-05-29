package hust.dao;

import org.apache.ibatis.session.SqlSession;

import hust.bean.Emp;
import hust.utils.MybatisUtil;

public class EmpDao {

	public void addEmp1() {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			//事务自动开始
			int cnt = sqlSession.insert(Emp.class.getName() + ".addEmp1"); //这就是为什么有的开发者会将Mapper的namespace写成类的全限定名
			System.out.println("Insert " + cnt + " row successfully.");
			sqlSession.commit();//[DEBUG,JdbcTransaction,main] Setting autocommit to false on JDBC Connection 默认自动提交设置关闭，因此必须要有commit
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			throw new RuntimeException();
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}
	
	public void addEmp2(Emp emp) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			sqlSession.insert(Emp.class.getName() + ".addEmp2", emp); 
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			throw new RuntimeException();
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}
	
	public static void main(String[] args) {
		EmpDao dao = new EmpDao();
		
		dao.addEmp1();
		dao.addEmp2(new Emp(2, "britar", "1991", "131"));
	}
}
