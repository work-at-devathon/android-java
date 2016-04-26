package com.example.crypsis.retro;


public class Customer {
    protected String seller_image;
    protected String name;
    protected String seller_name;
    protected String image_url;
    protected String display_price;


    //Getters and setters
    public String getSellerImage() {
        return seller_image;
    }

    public void setSellerImage(String sellerImage) {
        this.seller_image = sellerImage;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
    public String getSellerName() {
        return seller_name;
    }
    public void setSellerName(String sellerName) {
        this.seller_name=sellerName;
    }
    public String getImageUrl() {
        return image_url;

    }
    public void setImageUrl(String imageUrl) {
        this.image_url=imageUrl;
    }

    public String getPrice() {
        return display_price;
    }

    public void setPrice(String price) {
        this.display_price = price;
    }

    @Override
public String toString()
{
    return this.name;
}
}
