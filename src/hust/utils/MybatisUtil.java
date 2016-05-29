package hust.utils;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * sqlSession管理工具类
 * @author liangjian
 *
 */
public class MybatisUtil {
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	private static SqlSessionFactory sqlSessionFactory;
	
	/**
	 * 加载mybatis.xml配置文件
	 * notes：Resources对象是org.apache.ibatis.io.Resources。
	 */
	static {
		Reader reader;
		try {
			reader = Resources.getResourceAsReader("mybatis.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 禁止外界通过new方法创建
	 */
	private MybatisUtil(){}
	
	/**
	 * 获取SqlSession
	 */
	public static SqlSession getSqlSession() {
		SqlSession sqlSession = threadLocal.get();
		if(sqlSession == null) {
			sqlSession = sqlSessionFactory.openSession();
			//将sqlSession与当前线程绑定在一起
			threadLocal.set(sqlSession);
		}
		return sqlSession;
	}
	
	/**
	 * 关闭SqlSession，使其与当前线程分开
	 */
	public static void closeSqlSession() {
		//从当前线程中获取SQLSession对象
		SqlSession sqlSession = threadLocal.get();
		if (sqlSession != null) {
			sqlSession.close();
			//分开当前线程与SQLSession对象的关系，目的是让GC尽早回收
			threadLocal.remove();
		}
	}
	
	
	/**
	 * 测试
	 */
	public static void main(String[] args) {
		Connection conn = MybatisUtil.getSqlSession().getConnection();
		System.out.println(conn != null ? "success" : "fail");
	}
}
