package com.model2.mvc.service.purchase.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;

/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {  "classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class PurchaseServiceTest {

	// ==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	@Test
	public void testAddPurchase() throws Exception {

		Purchase purchase = new Purchase();

		Product product = new Product();
		product.setProdNo(10023);

		User buyer = new User();
		buyer.setUserId("user07");

		purchase.setPurchaseProd(product);
		purchase.setBuyer(buyer);
		purchase.setPaymentOption("1");
		purchase.setReceiverName("김돌돌");
		purchase.setReceiverPhone("010-1111-2222");
		purchase.setDivyAddr("서울");
		purchase.setDivyRequest("천천히 오세요");
		purchase.setTranCode("0");
		purchase.setDivyDate("20/11/17");

		purchaseService.addPurchase(purchase);

		// ==> console 확인
		// System.out.println(purchase);

		// ==> API 확인

		//Assert.assertEquals("0", purchase.getTranCode());

	}

	//@Test
	public void testGetPurchase() throws Exception {

		Purchase purchase = new Purchase();

		purchase.setTranNo(10061);

		purchase= purchaseService.getPurchase(purchase.getTranNo());

		System.out.println(purchase);

	}

	//@Test
	public void TestPurcahseList() throws Exception {

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		
		User buyer = new User();
		buyer.setUserId("user07");

		//Map<String, Object> map = purchaseService.getPurchaseList(search, buyer.getUserId());

		Map<String, Object> map = new HashMap<String, Object>();
		
		map = purchaseService.getPurchaseList(search, buyer.getUserId());
		
		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(3, list.size());

		// System.out.println(list);

		Integer totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);

	}
	//@Test
	public void TestUpdatePurchase() throws Exception{
		Purchase purchase = new Purchase();
		
		purchase.setTranNo(10048);
		purchase.setPaymentOption("2");
		purchase.setReceiverName("노진구");
		purchase.setReceiverPhone("010-1234-1234");
		purchase.setDivyAddr("울릉도");
		purchase.setDivyDate("20/11/19");
		purchase.setDivyRequest("문앞에~");
		
		purchaseService.updatePurcahse(purchase);
		
		purchase = purchaseService.getPurchase(purchase.getTranNo());
		
		Assert.assertEquals("노진구", purchase.getReceiverName());
		
		
		
	}
	
	//@Test
	public void TestUpdateTranCode() throws Exception{
		
		Purchase purchase = new Purchase();
		Product product = new Product();
		product.setProdNo(10040);
		
		purchase.setPurchaseProd(product);
		purchase.setTranCode("1");
		
		purchaseService.updateTranCode(purchase);
		
		purchase = purchaseService.getPurchase(10054);
		
		Assert.assertEquals("2", purchase.getTranCode().trim());
		
		
		
		
	}

}