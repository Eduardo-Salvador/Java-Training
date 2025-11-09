package Collections.Iterator_ListArray_Conversion.Application;
import Collections.Iterator_ListArray_Conversion.Controller.CityManager;
import Collections.Iterator_ListArray_Conversion.Domain.City;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        City c1 = new City("New York", 8400000);
        City c2 = new City("Los Angeles", 4000000);
        City c3 = new City("Chicago", 2700000);
        City c4 = new City("Houston", 2300000);
        City c5 = new City("Miami", 470000);
        List<City> cityList = new ArrayList<>(List.of(c1, c2, c3, c4, c5));

        System.out.println(cityList);
        System.out.println("------------------");

        CityManager.removeSmallCities(cityList, 1000000);
        System.out.println("------------------");

        System.out.println(cityList);
        System.out.println("------------------");

        City[] cityArray = CityManager.convertListToArray(cityList);
        System.out.println("------------------");

        cityList = CityManager.convertArrayToList(cityArray);
        System.out.println("------------------");

        CityManager.addCity(cityList, c5);
        System.out.println("-----------------");

        CityManager.addCity(cityList, c5);
        System.out.println("-----------------");

        System.out.println(cityList);
    }
}