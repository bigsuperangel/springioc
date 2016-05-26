package com.linyu.springioc.main;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.linyu.springioc.bean.A;
import com.linyu.springioc.bean.B;

public class Test {
	public static void main(String[] args) {
//		BeanFactory bf = new ClassPathXmlApplicationContext("/spring.xml");
//		B b = (B) bf.getBean("B");
//		System.out.println(b.getA().getName());
		
		final List<BigDecimal> prices = Arrays.asList(
				new BigDecimal("10"), new BigDecimal("30"), new BigDecimal("17"),
				new BigDecimal("20"), new BigDecimal("15"), new BigDecimal("18"),
				new BigDecimal("45"), new BigDecimal("12"));
		
		
		final BigDecimal totalOfDiscountedPrices =
				prices.stream()
				.filter(price -> price.compareTo(BigDecimal.valueOf(20)) > 0)
				.map(price -> price.multiply(BigDecimal.valueOf(0.9)))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
				System.out.println("Total of discounted prices: " + totalOfDiscountedPrices);
	}
}
