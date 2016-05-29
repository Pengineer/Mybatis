package hust.dynamic.dao;

import hust.bean.Emp;
import hust.utils.MybatisUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class EmpDynamicDao {

	/**
	 * Mybatis动态SQL实现初级/高级检索
	 */
	public List<Emp> highFindByConditions(Integer id, String name, String birth, String phone) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("pid", id);
			paraMap.put("pname", name);
			paraMap.put("pbirth", birth);
			paraMap.put("pphone", phone);
			return sqlSession.selectList("empNamespace.highFindByConditions", paraMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}
	
	/**
	 * Mybatis动态SQL实现动态更新
	 */
	public void dynamicUpdate(Integer id, String name, String birth, String phone) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("pid", id);
			paraMap.put("pname", name);
			paraMap.put("pbirth", birth);
			paraMap.put("pphone", phone);
			sqlSession.update("empNamespace.dynamicUpdate", paraMap);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}
	
	public static void main(String[] args) {
		EmpDynamicDao dao = new EmpDynamicDao();
		List<Emp> emps = dao.highFindByConditions(null, "britar", null, "131");
		for (Emp emp : emps) {
			System.out.println(emp.getId() + "\t" + emp.getName() + "\t" + emp.getBirth() + "\t" + emp.getPhone());
		}
		
		dao.dynamicUpdate(7, null, "1992", "132");
	}
}
