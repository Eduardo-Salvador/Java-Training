package Collections.Iterator.ListArray.Conversion.Domain;

public class City {
    private String name;
    private Integer population;

    public City(String name, Integer population) {
        this.name = name;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public Integer getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        return name + " - " + population;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(this == obj) return true;
        if(this.getClass() != obj.getClass()) return false;
        City city = (City) obj;
        return name != null && name.equals(city.getName());
    }
}