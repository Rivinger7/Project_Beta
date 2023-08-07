/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author satori
 */
public class InvoiceForm extends Invoice{

    public InvoiceForm() {
    }

    public InvoiceForm(Date date, User user, Customer customer, Customer IDCustomer, Book books, double totalPrice, int totalBooks) {
        super(date, user, customer, IDCustomer, books, totalPrice, totalBooks);
    }
    
    @Override
    public String toString() {
        return super.getIdInvoice() + "," + super.getName() + "," + super.getTotalPrice();
    }
    
}
