/*
*HW 3 -> Countries Using Built-In ArrayList and LinkedList
*Sarah Ferguson
*November 2017
*Program will read a list of countries and give choices for user to choice from to list data.
*/
public class Borders {
    //PIV
    public String country1, country2;

    //Constructors
    public Borders() {
        country1 = "";
        country2 = "";
    }

    public Borders(String country1, String country2) {
        this.country1 = country1;
        this.country2 = country2;
    }

    //Setter
    public void setCountry1(String name) {
        country1 = name;
    }

    public void setCountry2(String name) {
        country2 = name;
    }

    //Getter
    public String getBorder(String countryName) {
        //returns the border name from a given country name
        if (countryName.equalsIgnoreCase(country1))
            return country2;
        else if (countryName.equalsIgnoreCase(country2))
            return country1;
        else
            return "\nBorder could not be found.";
    }

}
