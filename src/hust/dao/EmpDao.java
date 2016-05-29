package hust.dao;

import hust.bean.Emp;
import hust.utils.MybatisUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

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
	
	public Emp findById(int id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			Emp emp = sqlSession.selectOne(Emp.class.getName() + ".findById", id);
			return emp;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}
	
	public List<Emp> findAll() {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			List<Emp> emps = sqlSession.selectList(Emp.class.getName() + ".findAll");
			return emps;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}
	
	public void update(Emp emp) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			sqlSession.update(Emp.class.getName() + ".update", emp);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}
	
	public void delete(Emp emp) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			sqlSession.delete(Emp.class.getName() + ".delete", emp);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}
	
	/**
	 * 有条件的分页查询
	 * 如果sql条件大于1个，可使用map封装
	 */
	public List<Emp> findAllWithFY(String name, int start, int count) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			Map<String, Object> para = new HashMap<String, Object>();
			para.put("pname", "%"+ name + "%");
			para.put("pstart", start);
			para.put("pcount", count);
			return sqlSession.selectList(Emp.class.getName() + ".findAllWithFY", para);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}
	
	public static void main(String[] args) {
		EmpDao dao = new EmpDao();
		
//		dao.addEmp1();
//		dao.addEmp2(new Emp(2, "britar", "1992", "13125"));
//		dao.addEmp2(new Emp(3, "britar", "1993", "13125"));
		
//		Emp emp = dao.findById(2);
//		System.out.println(emp.getId() + "\t" + emp.getName() + "\t" + emp.getBirth() + "\t" + emp.getPhone());

//		List<Emp> emps = dao.findAll();
//		for (Emp emp : emps) {
//			System.out.println(emp.getId() + "\t" + emp.getName() + "\t" + emp.getBirth() + "\t" + emp.getPhone());
//		}
		
//		Emp emp = dao.findById(2);
//		emp.setBirth("1993");
//		dao.update(emp);
		
//		Emp emp = dao.findById(3);
//		dao.delete(emp);
		
		List<Emp> emps = null;
		int page = 0;
		do {
			emps = dao.findAllWithFY("bri", page * 3, 3);
			if(emps == null || emps.size() == 0) break;
			System.out.println("第 " + (page+1) + " 页------------------------");
			for (Emp emp : emps) {
				System.out.println(emp.getId() + "\t" + emp.getName() + "\t" + emp.getBirth() + "\t" + emp.getPhone());
			}
			page++;
		} while (emps != null && emps.size() > 0);
	}
}
