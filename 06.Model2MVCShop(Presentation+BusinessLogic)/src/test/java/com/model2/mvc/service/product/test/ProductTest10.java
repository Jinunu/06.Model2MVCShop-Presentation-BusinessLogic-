package com.model2.mvc.service.product.test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public class ProductTest10 {

	public static void main(String[] args) throws IOException {
		
Reader reader = Resources.getResourceAsReader("sql/mybatis-config.xml");
		
		//==> 2. Reader ��ü�� �̿� xml metadata �� ������ ���� ������ ����, ��밡���� 
		//==>     SqlSession�� �����ϴ� SqlSessionFactory  instance ����
		SqlSessionFactory sqlSessionFactory 
											= new SqlSessionFactoryBuilder().build(reader);
		//==>3. SqlSessionFactory �� ���� autoCommit true �� SqlSession instance ����
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		
		Product product = new Product("��Ʈ�� �̹���","2000-10-10", 5000, "�Ｚ ����", "����2000");
		product.setManuDate("20100819");
		
		
		
////		
//		System.out.println("1.addProduct : ?");
//		System.out.println(sqlSession.insert("productMapper.addProduct",product));
	
		
		  product.setProdNo(10023); System.out.println(product);
		  
		  System.out.println();
		  System.out.println(sqlSession.selectOne("productMapper.findProduct",product.getProdNo()).toString());
		  
		 	  
		 			
		Search search = new Search();
		//search.setSearchCondition("2");
		
		
		
		System.out.println(sqlSession.selectList("productMapper.getProductList",search));
//			
//		product.setProdName("�������̸�");
//		
//		System.out.println(sqlSession.update("productMapper.updateProduct", product));
//		
		
			
	}

}
