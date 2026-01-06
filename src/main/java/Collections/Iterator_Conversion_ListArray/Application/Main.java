package Collections.Iterator_Conversion_ListArray.Application;
import Collections.Iterator_Conversion_ListArray.Services.CityManager;
import Collections.Iterator_Conversion_ListArray.Domain.City;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        City c1 = new City("New York", 8_400_000);
        City c2 = new City("Los Angeles", 4_000_000);
        City c3 = new City("Chicago", 2_700_000);
        City c4 = new City("Houston", 2_300_000);
        City c5 = new City("Miami", 470_000);
        List<City> cityList = new ArrayList<>(List.of(c1, c2, c3, c4, c5));

        System.out.println(cityList);
        System.out.println("------------------");

        CityManager.removeSmallCities(cityList, 1_000_000);
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