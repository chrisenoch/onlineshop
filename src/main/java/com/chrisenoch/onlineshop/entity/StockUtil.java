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

	//Add Java docs    //Map<Integer, Integer>> RequestedQuantity  Stock
	/**
	 * Compares the user's line items with the quantity of stock available.
	 * @return a map of products. Each product entry in turn contains a map with the keys representing the requested quantity of a product by a user
	 * and values representing the available quantity of the requested item by a user.
	 */
	public Map<Product, Map<Integer, Integer>> compareLineItemsWithStock(){
		Map<Product, Map<Integer, Integer>> comparedLineItemsWithStock = new HashMap<>();
		List<LineItem> lineItems = order.getLineItems();
		for (LineItem lineItem : lineItems) {
			Integer requestedQuantity = lineItem.getQuantity();
			Integer availableQuantity = lineItem.getProduct().getStock();
			
			Map<Integer, Integer> tempMap = new HashMap<>();
			tempMap.put(requestedQuantity, availableQuantity);
			comparedLineItemsWithStock.put(lineItem.getProduct(), tempMap );	
		}
		
		return comparedLineItemsWithStock;
	}
	
	//Add Java docs
	//Only adds to map if there was a problem with stock count. Products in basket unaffected by new stock counts do not get added to this map.
	/**
	 * 
	 * @param comparedLineItemsWithStock
	 * @return a map of products which the user requested, but there wasn't enough stock available. Each product entry
	 * contains a map with the keys representing the requested quantity of a product by the user
	 * and values representing the available quantity of the requested item by the user.
	 */
	public Map<Product, Map<Integer, Integer>> getLineItemAndStockDiscrepancies(
			Map<Product, Map<Integer, Integer>> comparedLineItemsWithStock){ 
		
		Map<Product, Map<Integer, Integer>> lineItemAndStockDiscrepancies = new HashMap<>();
		for (Entry<Product, Map<Integer, Integer>> map1 : comparedLineItemsWithStock.entrySet()) {
			Product product = map1.getKey();
			
			for (Entry<Integer, Integer> map2 : map1.getValue().entrySet()) {
				Integer requestedQuantity = map2.getKey();
				Integer availableQuantity = map2.getValue();
				
				if (availableQuantity - requestedQuantity < 0) {
					Map<Integer, Integer> tempMap = new HashMap<>();
					tempMap.put(requestedQuantity, availableQuantity);
					lineItemAndStockDiscrepancies.put(product, tempMap);
				} 
			}
			
		}	
		return lineItemAndStockDiscrepancies;
	}
	
	
	

}
