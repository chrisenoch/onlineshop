package com.chrisenoch.onlineshop.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StockUtil {
	
	private Order order;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public StockUtil(Order order) {
		super();
		this.order = order;
	}

	//Add Java docs
	public Map<Product, Map<Integer, Integer>> compareOrderContentsWithStock(){
		Map<Product, Map<Integer, Integer>> comparedOrderContentsWithStock = new HashMap<>();
		List<OrderContents> orderContents = order.getOrderContents();
		for (OrderContents oc : orderContents) {
			Integer requestedQuantity = oc.getQuantity();
			Integer availableQuantity = oc.getProduct().getStock();
			
			Map<Integer, Integer> tempMap = new HashMap<>();
			tempMap.put(requestedQuantity, availableQuantity);
			comparedOrderContentsWithStock.put(oc.getProduct(), tempMap );	
		}
		
		return comparedOrderContentsWithStock;
	}
	
	//Add Java docs
	//Only adds to map if there was a problem with stock count. Products in basket unaffected by new stock counts do not get added to this map.
	public Map<Product, Map<Integer, Integer>> updateOrderContentsAccordingToStock(
			Map<Product, Map<Integer, Integer>> comparedOrderContentsWithStock){ 
		
		Map<Product, Map<Integer, Integer>> newOrderContentsQuantities = new HashMap<>();
		for (Entry<Product, Map<Integer, Integer>> map1 : comparedOrderContentsWithStock.entrySet()) {
			Product product = map1.getKey();
			
			for (Entry<Integer, Integer> map2 : map1.getValue().entrySet()) {
				Integer requestedQuantity = map2.getKey();
				Integer availableQuantity = map2.getValue();
				
				if (availableQuantity - requestedQuantity < 0) {
					Map<Integer, Integer> tempMap = new HashMap<>();
					tempMap.put(requestedQuantity, availableQuantity);
					newOrderContentsQuantities.put(product, tempMap);
				} 
			}
			
		}	
		return newOrderContentsQuantities;
	}
	
	
	

}
