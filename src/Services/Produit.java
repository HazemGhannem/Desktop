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
public enum Produit {
    PIZZA("PIZZA.png",8.5f),BURGER("BURGER.png",5.0f),MAKLOUB("BURGER.png",6.5f),PASTA("BURGER.png",12.5f), KOUSKOUS("PIZZA.png",10.0f);

    private String image;
    private float prix;

    Produit(String image,float price){
        this.image = image;
        this.prix = price;
    }

    public String getImage() {
        return image;
    }

    public float getPrix() {
        return prix;
    }
}
