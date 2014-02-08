package org.uditha.lab4;

/*Problem : Passengers arrive to the bus stop and wait in a queue ;
When a bus arrive max no of passengers that can aboard is 50 ;
All the passengers arrive while bus is at the bus-stop waits for the next bus.*/

import java.util.concurrent.Semaphore;

public class Synchronized {  
	private int queue = 0; // No of passengers at the waiting queue
	private int passenger_id = 0; // No of all passengers
	private Semaphore mutex = new Semaphore(1);  // allow access to critical region
	private Semaphore bus = new Semaphore(0);  // signals when bus arrives
	private Semaphore boarded = new Semaphore(0); // signals when all are boarded
 
	// passenger boarded to the bus
	public void board() {  
		System.out.println("Passenger boarded");  
	}  
	
      // bus depart
	public void depart() {  
		System.out.println("Bus departed."); 
	}
	
	//Code for the Buses
	public void arrive() throws InterruptedException {  
		mutex.acquire();
		System.out.println("Bus arrived.");
		int n = Math.min(queue, 50);  // Bus signals its arrival only for <=50 passengers
		for(int i = 0; i < n; i++) {  
			bus.release();  // Signals the arrival of the bus
			boarded.acquire();  //  Signals boarded to the bus
		}  
		queue = Math.max((queue - 50), 0);  // Get the waiting passengers in the Queue 
		mutex.release();  
		depart();  // Bus depart
	}
	
	//Code for Passengers
	public void travel() throws InterruptedException {  
		mutex.acquire(); // Enter critical region
		passenger_id++; // Id of the passenger in the queue
		System.out.println("Passenger "+passenger_id+" came to the queue");
		queue++;  // Increment the waiting passenger count
		System.out.println("Number of waiting passengers = "+queue);
		mutex.release(); // Leave critical region
		bus.acquire(); // Wait until bus arrives
		board();  // Boarded when bus arrived
		boarded.release(); // Signal he is boarded 
	}  
}