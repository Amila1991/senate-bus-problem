package org.uditha.lab4;

/*Bus init worker ;Instantiated multiple times on top of one Synchronized object*/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BusInit {

	Synchronized target; 
	ExecutorService service = Executors.newFixedThreadPool(50);
	long threadId;

	public BusInit(Synchronized targ) {
		target = targ;
	}

	public void go(){
		try {
			service.submit(new Runnable() {
				public void run() {
					try {
						threadId = Thread.currentThread().getId();
						System.out.println("Thread # " + threadId + " Created for Bus Arrive"); // create thread for a bus
						target.arrive(); // Initiate method for bus arrive to bus top
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});

		} finally {
			service.shutdown();
		}
	}

}
