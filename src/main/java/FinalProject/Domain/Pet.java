package FinalProject.Domain;
import FinalProject.Exception.PetValidationException;
import lombok.Setter;

@Setter
public class Pet {
    private Long id;
    private String name;
    private PetType type;
    private PetSex sex;
    private Double age;
    private PetStatus status;
    private Double weight;
    private String breed;
    private String address;

    private Pet(){}

    public Long getId(){
        return id;
    }

    public String getAddress() {
        return address;
    }

    public Double getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public String getName() {
        return name;
    }

    public PetSex getSex() {
        return sex;
    }

    public PetStatus getStatus() {
        return status;
    }

    public PetType getType() {
        return type;
    }

    public Double getWeight() {
        return weight;
    }

    public static final class PetBuilder {
        private Long id;
        private String name;
        private PetType type;
        private PetSex sex;
        private Double age;
        private PetStatus status;
        private Double weight;
        private String breed;
        private String address;

        private PetBuilder() {
        }

        public static PetBuilder aPet() {
            return new PetBuilder();
        }

        public PetBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PetBuilder withType(PetType type) {
            this.type = type;
            return this;
        }

        public PetBuilder withSex(PetSex sex) {
            this.sex = sex;
            return this;
        }

        public PetBuilder withAge(Double age) {
            this.age = age;
            return this;
        }

        public PetBuilder withStatus(PetStatus status){
            this.status = status;
            return this;
        }

        public PetBuilder withWeight(Double weight){
            this.weight = weight;
            return this;
        }

        public PetBuilder withBreed(String breed){
            this.breed = breed;
            return this;
        }

        public PetBuilder withAddress(String address){
            this.address = address;
            return this;
        }

        public Pet build() throws PetValidationException {
            if (this.name == null || this.name.isBlank()) throw new PetValidationException("Name is required");
            if (this.type == null) throw new PetValidationException("Type is required");
            if (this.sex  == null) throw new PetValidationException("Sex is required");
            if (this.age == null) throw new PetValidationException("Age is required");
            if (this.age < 0 || this.age > 20) throw new PetValidationException("Invalid Age");
            if (this.weight <  0 || this.weight > 60) throw new PetValidationException("Invalid Weight");

            Pet pet = new Pet();
            pet.id      = this.id;
            pet.name    = this.name;
            pet.type    = this.type;
            pet.sex     = this.sex;
            pet.age     = this.age;
            pet.weight  = this.weight;
            pet.breed   = this.breed;
            pet.address = this.address;
            pet.status  = this.status;
            return pet;
        }
    }
}