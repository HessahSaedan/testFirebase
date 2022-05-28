package com.example.myapplication;

public class Car
{
    private String key;
    private String model;
    private String plate;
    private int price;
    private int year;

    public Car()
    {
        model = "NA";
        plate = "AAA1234";
        price = 1000;
        year  = 2022;
    }

    public Car(String model, String plate, int price, int year)
    {
        this.model = model;
        this.plate = plate;
        this.price = price;
        this.year = year;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public void setPlate(String plate)
    {
        this.plate = plate;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public String getModel()
    {
        return model;
    }

    public String getPlate()
    {
        return plate;
    }

    public int getPrice()
    {
        return price;
    }

    public int getYear()
    {
        return year;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    @Override
    public String toString()
    {
        return "Car{" +
                "key='" + key + '\'' +
                ", model='" + model + '\'' +
                ", plate='" + plate + '\'' +
                ", price=" + price +
                ", year=" + year +
                '}';
    }
}