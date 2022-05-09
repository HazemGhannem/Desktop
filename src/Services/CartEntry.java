/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author alaed
 */
public class CartEntry {
    private Produit produit;
    private int quantity;

    public CartEntry(Produit produit, int quantity) {
        this.produit = produit;
        this.quantity = quantity;
    }

    public Produit getProduit() {
        return produit;
    }

    public int getQuantity() {
        return quantity;
    }
    
     public void increaseQuantity() {
       this.quantity++;
    }
      public void decreaseQuantity() {
      if (this.quantity>0){
          this.quantity--;
      }
    }
    
}
