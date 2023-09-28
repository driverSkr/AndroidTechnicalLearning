package com.example.learnKotlin.chapter2;

import androidx.annotation.NonNull;

import com.example.learnKotlin.chapter3.StandardFunctionKt;

import java.util.Objects;

public class CellphoneJava {
    String brand;
    double price;

    public CellphoneJava(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    @NonNull
    @Override
    public String toString() {
        return "Cellphone{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellphoneJava cellphone = (CellphoneJava) o;
        return Double.compare(cellphone.price, price) == 0 && Objects.equals(brand, cellphone.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, price);
    }
}
