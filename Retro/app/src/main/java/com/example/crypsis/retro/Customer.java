package com.example.crypsis.retro;


public class Customer {
    private int seller_id;
    private String name;
    private String seller_name;
    private String seller_url;
    private String display_price;


    //Getters and setters
    public int getSellerId() {
        return seller_id;
    }

    public void setSellerId(int sellerId) {
        this.seller_id = sellerId;
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
    public String getSellerUrl() {
        return seller_url;
    }
    public void setSellerUrl(String sellerUrl) {
        this.seller_url=sellerUrl;
    }

    public String getPrice() {
        return display_price;
    }

    public void setPrice(String price) {
        this.display_price = price;
    }


}
