package com.example.demo.challenge01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SushiOrderManagerWithGroups extends SushiOrderManager{
    private int currentSeat;
    private int totalSeats;
    private ArrayList<Queue<SushiItem>> seats;
    
    private ArrayList<ArrayList<Queue<SushiItem>>> groups = new ArrayList<ArrayList<Queue<SushiItem>>>();
    
    public SushiOrderManagerWithGroups() {
        currentSeat = 0;
        totalSeats = 16;
        seats = new ArrayList<>();
    
        for (int i = 0; i < totalSeats; i++) {
            seats.add(new LinkedList<SushiItem>());
        }
    }
  
    public void addOrder(ArrayList<SushiOrder> request) {
    	
    	ArrayList<Queue<SushiItem>> group = new ArrayList<Queue<SushiItem>>(totalSeats);
    	for (int i = 0; i < totalSeats; i++) {
    		group.add(new LinkedList<SushiItem>());
    	}
    	
        for (SushiOrder order : request) {
        	
            for (String roll : order.getMenuItemIds()) {
            	group.get(order.getSeatId() - 1).offer(new SushiItem(order.getSeatId(), roll));
                seats.get(order.getSeatId() - 1).offer(new SushiItem(order.getSeatId(), roll));
            }
            
        }

        groups.add(group);
        
        System.out.println("######  addOrder #####");
        int groupNumber = 0;
        int seatNumber = 0;
        for(ArrayList<Queue<SushiItem>> q : groups) {
        	System.out.println("Group " + ++groupNumber);
        	
        	for(Queue<SushiItem> seats : q) {
        		System.out.println("Seat " + ++seatNumber + " - "+ seats);
        	}
        	
        	seatNumber = 0;
        }
    }
    
    public boolean isGroupEmpty(ArrayList<Queue<SushiItem>> group) {
    	
    	int emptySeatsCount = 0;
    	for(Queue<SushiItem> s : group) {
    		if(s.isEmpty()) {
    			emptySeatsCount++;
    		}
    	}
    	
    	if(emptySeatsCount == 16) {
    		return true;
    	}
    	return false;
    }

    boolean existMoreItems = false;
    
    SushiItem lastItem = null;
    int lastGroupNumber = 0;
    
    public SushiItem nextItem() {

    	int groupNumber = 0;
    	
        for(ArrayList<Queue<SushiItem>> group : groups) {
        	
        	groupNumber++;
        	
        	if(isGroupEmpty(group)) {
        		currentSeat = 0;
        		continue;
        	}

            while(currentSeat < 16) {
            	Queue<SushiItem> currentSeatQueue = group.get(currentSeat);
                SushiItem item = currentSeatQueue.peek();
                ++currentSeat;
                
                if(item != null) {
                	
                	if(!(lastGroupNumber == groupNumber &&
                			lastItem.getSeatId().equals(item.getSeatId()))) {
                		continue;
                	}
                	
                	if(currentSeatQueue.size() > 0) {
                    	existMoreItems = true;
                    }
                	
                	currentSeatQueue.poll();
                	this.lastGroupNumber = groupNumber;
                	this.lastItem = item;

                	return item;
                } else {
                	if(currentSeat == 16 && existMoreItems) {
                		existMoreItems = false;
                		currentSeat = 0;
                	}
                }
                
            }
            
            existMoreItems = false;
    		currentSeat = 0;
            
        }
    	
    	System.out.println("======> Next Item");
    	return null;
    }
}