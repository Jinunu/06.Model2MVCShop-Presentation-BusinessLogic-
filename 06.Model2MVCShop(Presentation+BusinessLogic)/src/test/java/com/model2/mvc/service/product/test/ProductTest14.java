package com.model2.mvc.service.product.test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductDaoImpl;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class ProductTest14 {

	public static void main(String[] args) throws Exception {

		/*
		 * Reader reader = Resources.getResourceAsReader("sql/mybatis-config.xml");
		 * 
		 * //==> 2. Reader ��ü�� �̿� xml metadata �� ������ ���� ������ ����, ��밡���� //==> SqlSession��
		 * �����ϴ� SqlSessionFactory instance ����
		 * 
		 * SqlSessionFactory sqlSessionFactory = new
		 * SqlSessionFactoryBuilder().build(reader); //==>3. SqlSessionFactory �� ����
		 * autoCommit true �� SqlSession instance ���� SqlSession sqlSession =
		 * sqlSessionFactory.openSession(true);
		 * 
		 * ProductDaoImpl productImpl = new ProductDaoImpl();
		 * productImpl.setSqlSession(sqlSession);
		 * 
		 * ProductServiceImpl productService = new ProductServiceImpl();
		 * productService.setProductDao(productImpl);
		 */

		ApplicationContext context = new ClassPathXmlApplicationContext("/config/commonservice.xml");

		ProductService productService = (ProductService) context.getBean("productServiceImpl");

		Product product = new Product("��Ʈ�� �̹���", "2000-10-10", 5000, "�Ｚ ����", "����3000");
		product.setManuDate("20100819");

		 System.out.println("1.addProduct : ?");
		productService.addProduct(product);

		product.setProdNo(10023);

		System.out.println(product);

		System.out.println();
		System.out.println(productService.getProduct(product.getProdNo()));

		/*
		 * Search search = new Search(); search.setCurrentPage(1);
		 * search.setPageSize(3); search.setSearchCondition("1");
		 * search.setSearchKeyword("���üұ�"); Map<String, Object> map =
		 * productService.getProductList(search);
		 * 
		 * List<Object> list = (List<Object>) map.get("list");
		 * 
		 * for (Object obj : list) { System.out.println("����Ʈ ����" + obj); } ;
		 * 
		 * System.out.println(productService.getProductList(search)); //
		 * product.setProdName("�������̸�"); // // //
		 */
	}

}
