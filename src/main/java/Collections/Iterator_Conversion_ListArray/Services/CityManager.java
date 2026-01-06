package Collections.Iterator_Conversion_ListArray.Services;
import Collections.Iterator_Conversion_ListArray.Domain.City;
import java.util.*;

public class CityManager {
    public static void addCity(List<City> cities, City city) {
        if (cities.contains(city)) {
            System.out.println("City: " + city.getName() + " is already in the list.");
            return;
        }
        cities.add(city);
        System.out.println("City: " + city.getName() + " added successfully");
    }

    public static void removeSmallCities(List<City> cities, int minPopulation){
        Iterator<City> cityIterator = cities.iterator();
        try {
            while (cityIterator.hasNext()) {
                City city = cityIterator.next();
                if (city.getPopulation() < minPopulation) {
                    System.out.println("City removed: " + city.getName());
                    cityIterator.remove();
                }
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        //cities.removeIf(c -> c.getPopulation() < minPopulation);
    }

    public static City[] convertListToArray(List<City> cities){
        if (!cities.isEmpty()) {
            City[] citiesArray = cities.toArray(new City[0]);
            System.out.println("Array contents:");
            for(City c : citiesArray){
                System.out.println(c);
            }
            return citiesArray;
        }
        System.out.println("List is empty, try again");
        return null;
    }

    public static List<City> convertArrayToList(City[] cityArray){
        if (cityArray != null){
            List<City> cities = new ArrayList<>(Arrays.asList(cityArray));
            System.out.println("List contents:");
            cities.forEach(System.out::println);
            return cities;
        }
        System.out.println("Array is empty, try again");
        return null;
    }
}