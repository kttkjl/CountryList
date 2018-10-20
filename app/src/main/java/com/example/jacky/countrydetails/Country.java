package com.example.jacky.countrydetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Country implements Serializable
{
    private String flag;

    private String name;

    private String alpha3Code;

    private String capital;

    private String region;

    private int population;

    private double area;

    private ArrayList<String> borders;

    public void setFlag(String flag){
        this.flag = flag;
    }
    public String getFlag(){
        return this.flag;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setAlpha3Code(String alpha3Code){
        this.alpha3Code = alpha3Code;
    }
    public String getAlpha3Code(){
        return this.alpha3Code;
    }
    public void setCapital(String capital){
        this.capital = capital;
    }
    public String getCapital(){
        return this.capital;
    }
    public void setRegion(String region){
        this.region = region;
    }
    public String getRegion(){
        return this.region;
    }
    public void setPopulation(int population){
        this.population = population;
    }
    public int getPopulation(){
        return this.population;
    }
    public void setArea(double area){
        this.area = area;
    }
    public double getArea(){
        return this.area;
    }
    public void setBorders(ArrayList<String> borders){
        this.borders = borders;
    }
    public List<String> getBorders(){
        return this.borders;
    }
}
