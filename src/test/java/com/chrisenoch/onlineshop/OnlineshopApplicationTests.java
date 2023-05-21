package com.chrisenoch.onlineshop;

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
import com.chrisenoch.onlineshop.entity.LineItem;
import com.chrisenoch.onlineshop.entity.Product;
import com.chrisenoch.onlineshop.entity.StockUtil;

@SpringBootTest
class OnlineshopApplicationTests {

	@Mock
	Product product1;

	@Spy //@InjectMocks
	LineItem lineItem = new LineItem();
	
	@Mock
	Product product2;
	
	@Spy 
	LineItem lineItem2;
	
	Order order = new Order();
	
	@Test
	public void shouldReturnComparedLineItemsWithStock() {
		lineItem.setProduct(product1);
		lineItem2.setProduct(product2);

		Mockito.when(lineItem.getQuantity()).thenReturn(5);
		Mockito.when(lineItem.getProduct().getStock()).thenReturn(5);		
		Mockito.when(lineItem2.getQuantity()).thenReturn(7);
		Mockito.when(lineItem2.getProduct().getStock()).thenReturn(7);
		
		List<LineItem> oC = new ArrayList<>();
		oC.add(lineItem);
		oC.add(lineItem2);
		
		order.setLineItems(oC);
		
		Mockito.when(product1.getId()).thenReturn(5);
		Mockito.when(product2.getId()).thenReturn(7);
		
		StockUtil stockUtil = new StockUtil(order);
		Map<Product, Map<Integer, Integer>> comparedLineItemsWithStock = 
				stockUtil.compareLineItemsWithStock();
		
		Map<Integer, Integer> extractedValues1 = comparedLineItemsWithStock.get(product1);
		Map<Integer, Integer> extractedValues2 = comparedLineItemsWithStock.get(product2);
		
		assertEquals(product1, lineItem.getProduct()); 
		assertEquals(product2, lineItem2.getProduct()); 
		assertEquals(2, order.getLineItems().size());
		assertThat(comparedLineItemsWithStock).containsKeys(product1, product2);
		
		assertThat(extractedValues1).containsExactlyEntriesOf(new HashMap<>(Map.of(5, 5)));
		assertThat(extractedValues2).containsExactlyEntriesOf(new HashMap<>(Map.of(7, 7)));
	}
	
	@Test
	public void shouldReturnEmptyMapWhenRequestedQuantityIsSameAsAvailableQuantity() {
		Map<Integer, Integer> innerMap = Map.of(5, 5);
		Map<Integer, Integer> innerMap2 = Map.of( 7, 7);
		Map<Product, Map<Integer, Integer>> comparedLineItemsWithStock = 
				Map.of(product1, innerMap, product2, innerMap2);
		
		StockUtil stockUtil = new StockUtil(order);
		Map<Product, Map<Integer, Integer>> lineItemAndStockDiscrepancies = 
				stockUtil.getLineItemAndStockDiscrepancies(comparedLineItemsWithStock);
		
		assertThat(lineItemAndStockDiscrepancies).isEmpty();
	}
	
	@Test
	public void shouldReturnEmptyMapWhenRequestedQuantityIsLessThanAvailableQuantity() {
		Map<Integer, Integer> innerMap = Map.of(4, 5);
		Map<Integer, Integer> innerMap2 = Map.of( 7, 7);
		Map<Product, Map<Integer, Integer>> comparedLineItemsWithStock = 
				Map.of(product1, innerMap, product2, innerMap2);
		
		StockUtil stockUtil = new StockUtil(order);
		Map<Product, Map<Integer, Integer>> lineItemAndStockDiscrepancies = 
				stockUtil.getLineItemAndStockDiscrepancies(comparedLineItemsWithStock);
		
		assertThat(lineItemAndStockDiscrepancies).isEmpty();
	}
	
	@Test
	public void shouldReturnMapWhenRequestedQuantityIsMoreThanAvailableQuantity() {
		Map<Integer, Integer> innerMap = Map.of(7, 5);
		Map<Integer, Integer> innerMap2 = Map.of( 7, 7);
		Map<Product, Map<Integer, Integer>> comparedLineItemsWithStock = 
				Map.of(product1, innerMap, product2, innerMap2);
		
		StockUtil stockUtil = new StockUtil(order);
		Map<Product, Map<Integer, Integer>> lineItemAndStockDiscrepanciesk = 
				stockUtil.getLineItemAndStockDiscrepancies(comparedLineItemsWithStock);
		
		Map<Integer, Integer> extractedMap = lineItemAndStockDiscrepanciesk.get(product1);
		assertThat(lineItemAndStockDiscrepanciesk).containsKey(product1);
		assertThat( extractedMap).containsEntry(7,  5);
	
	}


}
