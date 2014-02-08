package org.uditha.lab4;

/*Bus Passenger worker ;Instantiated multiple times on top of one Synchronized object*/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PassengerInit {

	Synchronized target;
	ExecutorService service = Executors.newFixedThreadPool(50);
	long threadId;

	public PassengerInit(Synchronized targ) {
		target = targ;
	}

	public void go(){
		try {

			for (int i = 0; i < 60; i++) {
				service.submit(new Runnable() {

					public void run() {
						try {
							threadId = Thread.currentThread().getId();
							System.out.println("Thread # " + threadId + " Created for Passenger");// create a thread for a passenger
							target.travel(); // call method for passenger go to bus-stop for a bus
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				});
			}

		} finally {
			service.shutdown();
		}
	}

}
