/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author satori
 */
public class Customer {
    private String name;
    private static int IDCustomer = 0;

    public Customer() {
    }
    
 
    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getIDCustomer() {
        return IDCustomer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIDCustomer(int IDCustomer) {
        this.IDCustomer = IDCustomer;
    }

    @Override
    public String toString() {
        return "Customer: " + this.name + " - ID:" + this.IDCustomer++;
    }
    
}
