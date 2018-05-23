package runtheworld.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import runtheworld.entity.Danmu;

import java.util.Date;
import java.util.List;

/**
 * @author evans 2018/5/6 21:40
 */

public class DanmuService4RedisTest extends ServiceTestBase {

	@Autowired
	DanmuService4Redis danmuService4Redis;
	@Test
	public void getLatestDanmu() {
		List<Danmu> result=danmuService4Redis.getLatestDanmu("view123",10 );
		for(Danmu obj:result){
			System.out.println(obj);
		}
	}

	@Test
	public void put() {
		Danmu danmu=new Danmu(123234,123,"I love love love here",new Date(),"#FFFFFF");
		long result=danmuService4Redis.put("view"+danmu.getViewId(), danmu);
		System.out.println(result);
	}

	@Test
	public void delete() {
	}
}