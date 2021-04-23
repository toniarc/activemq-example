package com.example.demo.challenge01;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class SushiOrderManagerTest {
    
	SushiOrderManager manager = new SushiOrderManagerWithGroups();
	
	@Test
    public void itShouldWorkOnRandomTests() {
		Queue<SushiItem> expected = queueify(new SushiItem[] {
	            null,
	            new SushiItem(3, "mango"),
	            new SushiItem(3, "dynamite"),
	            new SushiItem(3, "dynamite"),
	            null,
	            null,
	            new SushiItem(1, "dynamite"),
	            new SushiItem(4, "kimchi"),
	            new SushiItem(6, "cucumber")
	        });
		assertNextItem(manager, expected.poll());
		
		ArrayList<SushiOrder> request = new ArrayList<>(
	            Arrays.asList(
	                new SushiOrder(3, // seatId for this order
	                    new ArrayList<>(Arrays.asList(
	                        new String[] {
	                            // menuItemIds for this order
	                            "mango", "dynamite", "dynamite" 
	                        }
	                    ))
	                )
	            )
	        );
		
		addOrder(manager, request);
		assertNextItem(manager, expected.poll());
		assertNextItem(manager, expected.poll());
		assertNextItem(manager, expected.poll());
		assertNextItem(manager, expected.poll());
		assertNextItem(manager, expected.poll());
		
		request = new ArrayList<>(
	            Arrays.asList(
	                new SushiOrder(1, // seatId for this order
	                    new ArrayList<>(Arrays.asList(
	                        new String[] {
	                            // menuItemIds for this order
	                            "dynamite", "\"tuna\"", "mango", "lox" 
	                        }
	                    ))
	                ),
	                new SushiOrder(4, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {
		                            // menuItemIds for this order
		                            "kimchi", "kimchi", "mushroom" 
		                        }
		                    ))
		                )
	            )
	        );
		addOrder(manager, request);
		
		request = new ArrayList<>(
	            Arrays.asList(
	                new SushiOrder(6, // seatId for this order
	                    new ArrayList<>(Arrays.asList(
	                        new String[] {
	                            // menuItemIds for this order
	                        		"cucumber", "zucchini", "portobello", "portobello" 
	                        }
	                    ))
	                ),
	                new SushiOrder(9, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {
		                            // menuItemIds for this order
		                            "tuna", "tuna" 
		                        }
		                    ))
		                )
	            )
	        );
		addOrder(manager, request);
		
		request = new ArrayList<>(
	            Arrays.asList(
	                new SushiOrder(15, // seatId for this order
	                    new ArrayList<>(Arrays.asList(
	                        new String[] {
	                            // menuItemIds for this order
	                        		"cucumber" 
	                        }
	                    ))
	                )
	            )
	        );
		addOrder(manager, request);
		assertNextItem(manager, expected.poll());
		assertNextItem(manager, expected.poll());
		assertNextItem(manager, expected.poll());
	}
	
    @Test
    public void itShouldWorkAsAnIteratorWithASingleRequest() {
        Queue<SushiItem> expected = queueify(new SushiItem[] {
            new SushiItem(1, "tuna"),
            new SushiItem(2, "salmon"),
            new SushiItem(3, "avocado"),
            new SushiItem(1, "california"),
            new SushiItem(2, "tuna"),
            new SushiItem(1, "avocado"),
            new SushiItem(2, "crab"),
            new SushiItem(2, "scallop")
        });
        ArrayList<SushiOrder> request = new ArrayList<>(
            Arrays.asList(
                new SushiOrder(1, // seatId for this order
                    new ArrayList<>(Arrays.asList(
                        new String[] {
                            // menuItemIds for this order
                            "tuna", "california", "avocado" 
                        }
                    ))
                ),
                new SushiOrder(3,
                    new ArrayList<>(Arrays.asList(
                        new String[] {"avocado"}
                    ))
                ),
                new SushiOrder(2,
                    new ArrayList<>(Arrays.asList(
                        new String[] {
                            "salmon", "tuna", "crab", "scallop"
                        }
                    ))
                )
            )
        );
        
        System.out.println("Test 01");
        //SushiOrderManager manager = new SushiOrderManager();
        addOrder(manager, request);
        
        for (int i = 0; i < 8; i++) {
            assertNextItem(manager, expected.poll());
        }
    }
    
    @Test
    public void itShouldWorkWhenOrdersAndItemDeliveriesAreInterleaved() {
        Queue<SushiItem> expected = queueify(new SushiItem[] {
            new SushiItem(1, "avocado"),
            new SushiItem(2, "dragon"),
            new SushiItem(1, "dynamite"),
            new SushiItem(1, "mushroom"),
            null
        });
        ArrayList<SushiOrder> request = new ArrayList<>(
            Arrays.asList(
                new SushiOrder(2,
                    new ArrayList<>(Arrays.asList(
                        new String[] {"dragon"}
                    ))
                ),
                new SushiOrder(1,
                    new ArrayList<>(Arrays.asList(
                        new String[] {"avocado", "dynamite"}
                    ))
                )
            )
        );
        //SushiOrderManager manager = new SushiOrderManager();

        addOrder(manager, request);
        assertNextItem(manager, expected.poll());
        
        request = new ArrayList<>(
            Arrays.asList(
                new SushiOrder(1,
                    new ArrayList<>(Arrays.asList(
                        new String[] {"mushroom"}
                    ))
                )
            )
        );
        addOrder(manager, request);
        
        for (int i = 0; i < 4; i++) {
            assertNextItem(manager, expected.poll());
        }
    }
    
    @Test
    public void itShouldHandleRoundsAndGroups() {
        Queue<SushiItem> expected = queueify(new SushiItem[] {
            new SushiItem(13, "rainbow"),
            new SushiItem(13, "\"tuna\""),
            new SushiItem(1, "cucumber")
        });
        
        //SushiOrderManager manager = new SushiOrderManager();
        
        /*
         * Group1 = [{13,rainbow}, {13, tuna}]
         * Group2 = [{1,cucumber}]
         */
        
        ArrayList<SushiOrder> request = new ArrayList<>(
            Arrays.asList(
                new SushiOrder(13,
                    new ArrayList<>(Arrays.asList(
                        new String[] {"rainbow", "\"tuna\""}
                    ))
                )
            )
        );
        
        addOrder(manager, request);
        assertNextItem(manager, expected.poll());
        
        request = new ArrayList<>(
            Arrays.asList(
                new SushiOrder(1,
                    new ArrayList<>(Arrays.asList(
                        new String[] {"cucumber"}
                    ))
                )
            )
        );
        
        addOrder(manager, request);
        assertNextItem(manager, expected.poll());
        assertNextItem(manager, expected.poll());
    }
    
    private static void assertNextItem(
        SushiOrderManager manager, SushiItem expected
    ) {
        SushiItem item = manager.nextItem();
        System.out.println("<LOG::-Next Item>" + item);
        assertEquals(expected, item);
    }
    
    private static void addOrder(
        SushiOrderManager manager, 
        ArrayList<SushiOrder> request
    ) {
        System.out.println("<LOG::-Add Order>" + request);
        manager.addOrder(request);
    }
    
    private static Queue<SushiItem> queueify(SushiItem[] steps) {
        Queue<SushiItem> result = new LinkedList<>();
        
        for (SushiItem step : steps) {
            result.offer(step);
        }
        
        return result;
    }
}