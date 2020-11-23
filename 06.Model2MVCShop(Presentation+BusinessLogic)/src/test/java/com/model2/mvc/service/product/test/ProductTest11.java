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
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.impl.ProductDaoImpl;

public class ProductTest11 {

	public static void main(String[] args) throws Exception {
		
Reader reader = Resources.getResourceAsReader("sql/mybatis-config.xml");
		
		//==> 2. Reader 객체를 이용 xml metadata 에 설정된 각정 정보를 접근, 사용가능한 
		//==>     SqlSession을 생성하는 SqlSessionFactory  instance 생성
		SqlSessionFactory sqlSessionFactory 
											= new SqlSessionFactoryBuilder().build(reader);
		//==>3. SqlSessionFactory 를 통해 autoCommit true 인 SqlSession instance 생성
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		
		ProductDaoImpl productImpl = new ProductDaoImpl();
		productImpl.setSqlSession(sqlSession);
		Product product = new Product("노트북 이미지","2000-10-10", 5000, "삼성 놑북", "센스2000");
		product.setManuDate("20100819");
		
		
		
		/*
		 * //// System.out.println("1.addProduct : ?");
		 * System.out.println("::"+productImpl.insertProduct(product));
		 */
		
		  product.setProdNo(10023); 
		  
		  System.out.println(product);
		  
		  System.out.println();
		  System.out.println(productImpl.findProduct(product.getProdNo()));
		 			
			
			  Search search = new Search(); //search.setSearchCondition("2");
			  
			  
			  
			  System.out.println(sqlSession.selectList("productMapper.getProductList",
			  search)); // // product.setProdName("수정된이름"); // //
			  System.out.println(sqlSession.update("productMapper.updateProduct",
			  product)); //
			  
			 		
	}

}
