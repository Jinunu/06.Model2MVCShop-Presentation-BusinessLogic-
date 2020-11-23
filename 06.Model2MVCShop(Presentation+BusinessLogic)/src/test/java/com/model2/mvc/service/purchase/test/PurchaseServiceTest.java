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
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {  "classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class PurchaseServiceTest {

	// ==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
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
		purchase.setReceiverName("�赹��");
		purchase.setReceiverPhone("010-1111-2222");
		purchase.setDivyAddr("����");
		purchase.setDivyRequest("õõ�� ������");
		purchase.setTranCode("0");
		purchase.setDivyDate("20/11/17");

		purchaseService.addPurchase(purchase);

		// ==> console Ȯ��
		// System.out.println(purchase);

		// ==> API Ȯ��

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
		purchase.setReceiverName("������");
		purchase.setReceiverPhone("010-1234-1234");
		purchase.setDivyAddr("�︪��");
		purchase.setDivyDate("20/11/19");
		purchase.setDivyRequest("���տ�~");
		
		purchaseService.updatePurcahse(purchase);
		
		purchase = purchaseService.getPurchase(purchase.getTranNo());
		
		Assert.assertEquals("������", purchase.getReceiverName());
		
		
		
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