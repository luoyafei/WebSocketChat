import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.socketchat.bean.User;
import com.socketchat.dao.IUserDao;

@ContextConfiguration("classpath:beans.xml")
public class WebSocketChatTest extends AbstractJUnit4SpringContextTests {

	@Resource(name="sqlSessionFactory")
	private SqlSessionFactory ssf;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		IUserDao ud = new ClassPathXmlApplicationContext("beans.xml").getBean("ud", IUserDao.class);
		/*User u = ss.selectOne("com.socketchat.bean.User.getOne", 2);
		ss.commit();
		ss.close();
		System.out.println(u.toString());*/
		
		/*User user = new User();
		user.setUserId(UUID.randomUUID().toString().replace("-", ""));
		user.setNickName("咯啊一份");
		user.setPassword("sklfjskfkl");
		user.setCreateDate(new Timestamp(System.currentTimeMillis()));
		user.setRemoteIp("111.111.111.111");
		boolean result = ud.insertUser(user);*/
		/*User user = ud.getOneUser("14fa7c68dec349bfb0418b40c231bcb6");
		System.out.println(user.toString());*/
		User user = new User();
		user.setNickName("罗亚飞");
		user.setPassword("luoyafei");
		System.out.println(ud.getOneUserFromNickAndPwd(user).toString());
	}
	
	@Resource(name="ud")
	private IUserDao ud; 
	@Test
	public void testIsExist() {
		User user = new User();
		user.setNickName("罗亚飞");
		user.setPassword("luoyafei");
		System.out.println(ud.getOneUserFromNickAndPwd(user).toString());
	}
	
	@Test
	public void testDAO() {
		User user = new User();
		user.setUserId(UUID.randomUUID().toString().replace("-", ""));
		user.setNickName("咯啊一份");
		user.setPassword("sklfjskfkl");
		user.setCreateDate(new Timestamp(System.currentTimeMillis()));
		user.setRemoteIp("111.111.111.111");
		//boolean result = ud.insertUser(user);
		//System.out.println(result);
	}
	
	
	
	@Test
	public void testMybatis() throws IOException {
		
		/*String resource = "mybatisConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);*/
		
		SqlSession session = ssf.openSession();
		
		List<User> us = session.selectList("com.socketchat.bean.User.allList");
		
		User u = session.selectOne("com.socketchat.bean.User.getOne", 6);
		session.commit();
		session.close();
		for(int i = 0; i < us.size(); i++) {
			System.out.println(us.get(i).toString());
		}
		System.out.println(u.toString());
	}
	
	@Test
	public void testInsert() {
		
		/*String resource = "mybatisConfig.xml";
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);*/
		SqlSession session = ssf.openSession();
		
		User user = new User();
		/*user.setUserName("咯啊运费");
		user.setAge("22");
		user.setSalary(123);*/
		
		int i = session.insert("com.socketchat.bean.User.insertUser", user);
		session.commit();
		System.out.println(i);
	}
	
	@Test
	public void testUpdate() {
		
		String resource = "mybatisConfig.xml";
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(resource);
		} catch(IOException e) {}
		
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
		
		SqlSession session = ssf.openSession();
		
		User user = session.selectOne("com.socketchat.bean.User.getOne", 1);
		/*user.setUserName("飞飞");*/
		int i = session.update("com.socketchat.bean.User.updateUser", user);
		session.commit();
		
		System.out.println(i);
	}
	
	@Test
	public void testDelete() {
		
		String resource = "mybatisConfig.xml";
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(resource);
		} catch(IOException e) {}
		
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
		
		SqlSession session = ssf.openSession();
		
		int i = session.delete("com.socketchat.bean.User.deleteUser", 6);
		session.commit();
		
		System.out.println(i);
		
	}
	
	
}
