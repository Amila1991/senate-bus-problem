package org.uditha.lab4;

/**/
public class Runner {
	public static void main(String args[]){
		
		Synchronized target = new Synchronized(); 
		PassengerInit obj_p1 = new PassengerInit(target); // create passenger object
		BusInit obj_b1 = new BusInit(target);		//create Bus object
		PassengerInit obj_p2 = new PassengerInit(target); 
		BusInit obj_b2 = new BusInit(target);
		BusInit obj_b3 = new BusInit(target);
		
		obj_b1.go(); // Call for the bus ; Come to the bus-stop and leave immediately due to no passengers
		obj_p1.go(); // Call for passengers;create passengers
		obj_b2.go(); // Arrive bus and load passengers available at the time of arrival(Max = 50) 
		obj_p2.go(); // Call for passengers ; create passengers
		obj_b3.go(); // Arrive next bus and load waiting passengers
	}
}

