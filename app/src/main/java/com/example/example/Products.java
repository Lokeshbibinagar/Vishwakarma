package com.example.example;

public class Products
{
    String _productTitle,_productName,_productPrice,_productQuantity,_productColor,_productDescription,productImageUrl;

    public Products(String _productTitle, String _productName, String _productPrice, String _productQuantity, String _productColor, String _productDescription, String productImageUrl) {
        this._productTitle = _productTitle;
        this._productName = _productName;
        this._productPrice = _productPrice;
        this._productQuantity = _productQuantity;
        this._productColor = _productColor;
        this._productDescription = _productDescription;
        this.productImageUrl = productImageUrl;
    }

    public String get_productTitle() {
        return _productTitle;
    }

    public void set_productTitle(String _productTitle) {
        this._productTitle = _productTitle;
    }

    public String get_productName() {
        return _productName;
    }

    public void set_productName(String _productName) {
        this._productName = _productName;
    }

    public String get_productPrice() {
        return _productPrice;
    }

    public void set_productPrice(String _productPrice) {
        this._productPrice = _productPrice;
    }

    public String get_productQuantity() {
        return _productQuantity;
    }

    public void set_productQuantity(String _productQuantity) {
        this._productQuantity = _productQuantity;
    }

    public String get_productColor() {
        return _productColor;
    }

    public void set_productColor(String _productColor) {
        this._productColor = _productColor;
    }

    public String get_productDescription() {
        return _productDescription;
    }

    public void set_productDescription(String _productDescription) {
        this._productDescription = _productDescription;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

}
