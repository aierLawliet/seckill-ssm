package seckill;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lin.seckill.dao.SeckillDao;
import com.lin.seckill.dao.cache.RedisDao;
import com.lin.seckill.entity.Seckill;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class RedisDaoTest {
	
	private long seckillId = 1001;
	
	@Autowired
	private RedisDao redisDao;
	
	@Autowired
	private SeckillDao seckillDao;
	
	@Test
	public void t1() throws Exception{
		Seckill seckill = redisDao.getSeckill(seckillId);
		if(seckill == null){
			seckill = seckillDao.queryById(seckillId);
			if(seckill != null){
				String result = redisDao.putSeckill(seckill);
				System.out.println(result);
				seckill = redisDao.getSeckill (seckillId);
				System.out.println(seckill);
			} 
		}
	}
}
