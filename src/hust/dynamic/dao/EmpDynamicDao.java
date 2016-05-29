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
	
	/**
	 * Mybatis动态SQL实现动态删除
	 * 输入参数可以写数组，也可以写可变参数类型 ——> 对应的Mapper.xml中的参数类型都是array
	 */
	public void dynamicDeleteArray(int... ids) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			sqlSession.delete("empNamespace.dynamicDeleteArray", ids);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}
	
	/**
	 * Mybatis动态SQL实现动态删除
	 * 输入参数为列表类型 ——> 对应的Mapper.xml中的参数类型都是list
	 */
	public void dynamicDeleteList(List<Integer> ids) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			sqlSession.delete("empNamespace.dynamicDeleteList", ids);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}
	
	/**
	 * Mybatis动态SQL实现动态插入
	 */
	public void dynamicInsert(Emp emp) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			sqlSession.delete("empNamespace.dynamicInsert", emp);
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
		
//		List<Emp> emps = dao.highFindByConditions(null, "britar", null, "131");
//		for (Emp emp : emps) {
//			System.out.println(emp.getId() + "\t" + emp.getName() + "\t" + emp.getBirth() + "\t" + emp.getPhone());
//		}
		
//		dao.dynamicUpdate(7, null, "1992", "132");
		
//		dao.dynamicDeleteArray(8, 10, 9999);
		
//		List<Integer> list = new ArrayList<Integer>();
//		list.add(9);
//		list.add(11);
//		list.add(12);
//		list.add(999);
//		dao.dynamicDeleteList(list);
		
		dao.dynamicInsert(new Emp(8, "Pony", "1970", "111"));
		dao.dynamicInsert(new Emp(9, null, "1970", "111"));
		dao.dynamicInsert(new Emp(10, null, "1970", null));
	}
}
