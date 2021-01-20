package com.chrisenoch.funtestapp.funtestapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.OrderContents;
import com.chrisenoch.onlineshop.entity.Product;
import com.chrisenoch.onlineshop.entity.StockUtil;

@SpringBootTest
class FuntestappApplicationTests {

	@Mock
	Product product1;

	@Spy //@InjectMocks
	OrderContents orderContents1 = new OrderContents();
	
	@Mock
	Product product2;
	
	@Spy 
	OrderContents orderContents2;
	
	Order order = new Order();
	
	@Test
	public void shouldReturnCompareOrderContentsWithStock() {
		orderContents1.setProduct(product1);
		orderContents2.setProduct(product2);

		Mockito.when(orderContents1.getQuantity()).thenReturn(5);
		Mockito.when(orderContents1.getProduct().getStock()).thenReturn(5);		
		Mockito.when(orderContents2.getQuantity()).thenReturn(7);
		Mockito.when(orderContents2.getProduct().getStock()).thenReturn(7);
		
		List<OrderContents> oC = new ArrayList<>();
		oC.add(orderContents1);
		oC.add(orderContents2);
		
		order.setOrderContents(oC);
		
		Mockito.when(product1.getId()).thenReturn(5);
		Mockito.when(product2.getId()).thenReturn(7);
		
		StockUtil stockUtil = new StockUtil(order);
		Map<Product, Map<Integer, Integer>> comparedOrderContentsWithStock = 
				stockUtil.compareOrderContentsWithStock();
		
		Map<Integer, Integer> extractedValues1 = comparedOrderContentsWithStock.get(product1);
		Map<Integer, Integer> extractedValues2 = comparedOrderContentsWithStock.get(product2);
		
		assertEquals(product1, orderContents1.getProduct()); 
		assertEquals(product2, orderContents2.getProduct()); 
		assertEquals(2, order.getOrderContents().size());
		assertThat(comparedOrderContentsWithStock).containsKeys(product1, product2);
		
		assertThat(extractedValues1).containsExactlyEntriesOf(new HashMap<>(Map.of(5, 5)));
		assertThat(extractedValues2).containsExactlyEntriesOf(new HashMap<>(Map.of(7, 7)));
	}
	
	@Test
	public void shouldReturnEmptyMapWhenRequestedQuantityIsSameAsAvailableQuantity() {
		Map<Integer, Integer> innerMap = Map.of(5, 5);
		Map<Integer, Integer> innerMap2 = Map.of( 7, 7);
		Map<Product, Map<Integer, Integer>> comparedOrderContentsWithStock = 
				Map.of(product1, innerMap, product2, innerMap2);
		
		StockUtil stockUtil = new StockUtil(order);
		Map<Product, Map<Integer, Integer>> updateOrderContentsAccordingToStock = 
				stockUtil.updateOrderContentsAccordingToStock(comparedOrderContentsWithStock);
		
		assertThat(updateOrderContentsAccordingToStock).isEmpty();
	}
	
	@Test
	public void shouldReturnEmptyMapWhenRequestedQuantityIsLessThanAvailableQuantity() {
		Map<Integer, Integer> innerMap = Map.of(4, 5);
		Map<Integer, Integer> innerMap2 = Map.of( 7, 7);
		Map<Product, Map<Integer, Integer>> comparedOrderContentsWithStock = 
				Map.of(product1, innerMap, product2, innerMap2);
		
		StockUtil stockUtil = new StockUtil(order);
		Map<Product, Map<Integer, Integer>> updateOrderContentsAccordingToStock = 
				stockUtil.updateOrderContentsAccordingToStock(comparedOrderContentsWithStock);
		
		assertThat(updateOrderContentsAccordingToStock).isEmpty();
	}
	
	@Test
	public void shouldReturnMapWhenRequestedQuantityIsMoreThanAvailableQuantity() {
		Map<Integer, Integer> innerMap = Map.of(7, 5);
		Map<Integer, Integer> innerMap2 = Map.of( 7, 7);
		Map<Product, Map<Integer, Integer>> comparedOrderContentsWithStock = 
				Map.of(product1, innerMap, product2, innerMap2);
		
		StockUtil stockUtil = new StockUtil(order);
		Map<Product, Map<Integer, Integer>> updateOrderContentsAccordingToStock = 
				stockUtil.updateOrderContentsAccordingToStock(comparedOrderContentsWithStock);
		
		Map<Integer, Integer> extractedMap = updateOrderContentsAccordingToStock.get(product1);
		assertThat(updateOrderContentsAccordingToStock).containsKey(product1);
		assertThat( extractedMap).containsEntry(7,  5);
	
	}


}
