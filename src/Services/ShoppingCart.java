/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alaed
 */
public class ShoppingCart {
    
    private static ShoppingCart INSTANCE;
    public static ShoppingCart getInstance(){
        if (INSTANCE == null){
            INSTANCE = new ShoppingCart();
        }
        return INSTANCE;
    }

    public ShoppingCart(Map<String, CartEntry> entries) {
        this.entries = entries;
    }
    
    private Map <String,CartEntry> entries;  // feha key w value 

    private ShoppingCart() {
        this.entries = new HashMap<>();
    }
    
    
    
    
      public void addProduct(String productName) {
         CartEntry productEntry= entries.get(productName.toUpperCase());
         if(productEntry!=null){
         
             productEntry.increaseQuantity();
         }else{
         
             Produit produit = Produit.valueOf(productName);
             CartEntry entry = new CartEntry (produit,1);
             entries.put(productName.toUpperCase(), entry);
         }
        
    }
      
      
      
    
      public void removeProduct(String productName) {
         CartEntry productEntry= entries.get(productName.toUpperCase());
         if(productEntry!=null){
         
             productEntry.decreaseQuantity();
         }
        
    }
      
      
      
      
      
      public int getQuantity(String productName) {
         CartEntry productEntry= entries.get(productName.toUpperCase());
         if(productEntry!=null){
         
            return productEntry.getQuantity();
         }else    { return 0; }
        
    }
      
      
      
      
      
      public float CalculateTotal() {
          
          float total =0;
          for (CartEntry entry:entries.values()){
              float entryCost = entry.getProduit().getPrix()* entry.getQuantity();
              total = total + entryCost ;
          }
        return total;
    }
      
      
      public String totalCommandeString() {
          
          String cs ="";
          for (CartEntry entry:entries.values()){
              String entryString = entry.getProduit().name();
              cs = cs + entryString + "+" ;
          }
        return cs;
    }
      
      
       public List <CartEntry> getEntries() {
          
          return new ArrayList<>(entries.values());
    }
      
      
}
