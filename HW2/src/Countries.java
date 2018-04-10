/*
*HW 2 -> Countries Using Array and LinkedList
*Sarah Ferguson
*November/December 2017
*Program will read a list of countries and give choices for user to choice from to list data.
*/
public class Countries {
    //PIV
    public String countryName, latitude, longitude;
    public int countryArea, countryPopulation, countryYear;
    public double countryGDP;
    //Constructors
    public Countries() {
        countryName = "";
        latitude = "";
        longitude = "";
        countryArea = 0;
        countryPopulation = 0;
        countryYear = 0;
        countryGDP = 0;
    }

    public Countries(String name, String lat, String longit, int area, int pop, double gdp, int year) {
        countryName = name;
        latitude = lat;
        longitude = longit;
        countryArea = area;
        countryPopulation = pop;
        countryYear = year;
        countryGDP = gdp;
    }

    //Setters
    public void setCountryName(String name) {
        countryName = name;
    }

    public void setLatitude(String lat) {
        latitude = lat;
    }

    public void setLongitude(String longit) {
        longitude = longit;
    }

    public void setCountryArea(int area) {
        countryArea = area;
    }

    public void setCountryPopulation(int pop) {
        countryPopulation = pop;
    }

    public void setCountryYear(int year) {
        countryYear = year;
    }

    public void setCountryGDP(double gdp) {
        countryGDP = gdp;
    }

    //Getters
    public String getCountryName() {
        return countryName;
    }

    public int getCountryPopulation() {
        return countryPopulation;
    }

}