package com.example.demo.challenge01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SushiOrderManager {
    private int currentSeat;
    private int totalSeats;
    private ArrayList<Queue<SushiItem>> seats;
    
    private Queue<SushiItem> sortedItems = new LinkedList<SushiItem>();
    private boolean existNewOrders = false;
 
    public SushiOrderManager() {
        currentSeat = 0;
        totalSeats = 16;
        seats = new ArrayList<>();
    
        for (int i = 0; i < totalSeats; i++) {
            seats.add(new LinkedList<SushiItem>());
        }
    }
  
    public void addOrder(ArrayList<SushiOrder> request) {
        for (SushiOrder order : request) {
            for (String roll : order.getMenuItemIds()) {
                seats.get(order.getSeatId() - 1).offer(
                    new SushiItem(order.getSeatId(), roll)
                );
            }
        }
        
        existNewOrders = true;
        
        System.out.println("######  addOrder #####");
        for(Queue<SushiItem> q : seats) {
        	System.out.println(q);
        }
    }
    
    private void sortSushiItems() {
    	int currentSeat = 0;
        boolean existMoreItems = false;
        while(currentSeat < 16) {
        	Queue<SushiItem> currentSeatQueue = seats.get(currentSeat);
            SushiItem item = currentSeatQueue.poll();
            ++currentSeat;
            
            if(currentSeatQueue.size() > 0) {
            	existMoreItems = true;
            }
            
            if(item != null) {
            	sortedItems.add(item);
            }
            
            if(existMoreItems && currentSeat == 16) {
            	currentSeat = 0;
            	existMoreItems = false;
            }
            
        }
        
        System.out.println("**** Sushi Items Ordered *****");
        for(SushiItem q : sortedItems) {
        	System.out.println(q);
        }
        
        existMoreItems = false;
    }

    boolean existMoreItems = false;
    
    public SushiItem nextItem() {
    	if(existNewOrders) {
    		sortSushiItems();
    	} 
    	
    	/*
        while(currentSeat < 16) {
        	Queue<SushiItem> currentSeatQueue = seats.get(currentSeat);
            SushiItem item = currentSeatQueue.peek(); //do not remove from queue
            ++currentSeat;
            
            if(item != null) {
            	
            	//if(this.lastItem != null && this.lastItem.getSeatId().equals(item.getSeatId())) {
            	//	continue;
            	//}
            	
            	currentSeatQueue.poll(); //remove from queue
            	existMoreItems = currentSeatQueue.size() > 0;
            	this.lastItem = item;
            	return item;
            }
            
            if(currentSeat == 16 && existMoreItems) {
            	currentSeat = 0;
            }
            
        }
        
        currentSeat = 0;
    	*/
    	
    	System.out.println("======> Next Item");
    	return sortedItems.poll();
    }
}