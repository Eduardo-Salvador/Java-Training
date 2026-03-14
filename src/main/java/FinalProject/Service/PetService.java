package FinalProject.Service;
import FinalProject.Domain.*;
import FinalProject.Exception.*;
import FinalProject.Repository.*;
import FinalProject.Server.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class PetService {
    private final PetRepository<Pet, Long> repository;
    private final NotificationServer notificationServer;

    // CopyOnWriteArrayList garante thread-safety nas operações de leitura concorrentes.
    // Escritas criam uma cópia interna da lista, evitando ConcurrentModificationException
    // CopyOnWriteArrayList ensures thread safety in concurrent read operations.
    // Writes create an internal copy of the list, preventing ConcurrentModificationException
    private final List<Pet> cache;

    public PetService(PetRepository<Pet, Long> repository, NotificationServer notificationServer) throws DatabaseConnectionException {
        this.repository = repository;
        this.notificationServer = notificationServer;
        this.cache = new CopyOnWriteArrayList<>(repository.findAll());
    }

    public PetResponseDTO register(PetRequestDTO petRequestDTO) throws PetValidationException {
        if (petRequestDTO != null){
            Pet newPet = PetFactory.create(petRequestDTO);
            if (PetValidator.validName(newPet.getName())
                && PetValidator.isValidAge(newPet.getAge())
                && PetValidator.isValidWeight(newPet.getWeight())
                && PetValidator.isValidAddress(newPet.getAddress())) {
                repository.save(newPet);
                cache.add(newPet);
                CompletableFuture.runAsync(() -> notificationServer.notify(newPet));
                return PetResponseDTO.fromPet(newPet);
            }
        }
        throw new PetValidationException("Invalid Request");
    }

    public Optional<PetResponseDTO> findById(Long id) throws PetNotFoundException {
        Optional<Pet> petFind = cache.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (petFind.isPresent()){
            return Optional.of(PetResponseDTO.fromPet(petFind.get()));
        }
        throw new PetNotFoundException("Pet not found for id: " + id);
    }

    public List<PetResponseDTO> findAll() {
        return cache.stream()
                .map(PetResponseDTO::fromPet)
                .toList();
    }

    public List<PetResponseDTO> findByNameAndBreed(String name, String breed) throws PetNotFoundException {
        List<PetResponseDTO> list = cache.stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase())
                        && p.getBreed().toLowerCase().contains(breed.toLowerCase()))
                .map(PetResponseDTO::fromPet)
                .toList();
        if (list.isEmpty()) throw new PetNotFoundException("Pet not found");
        return list;
    }

    public List<PetResponseDTO> findByType(PetType type) {
        return cache.stream()
                .filter(p -> p.getType() == type)
                .map(PetResponseDTO::fromPet)
                .toList();
    }

    public boolean adopt(Long id) throws PetNotFoundException {
        Optional<Pet> petFind = cache.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (petFind.isPresent()) {
            Pet pet = petFind.get();
            pet.setStatus(PetStatus.ADOPTED);
            repository.update(pet);
            cache.replaceAll(p -> p.getId().equals(pet.getId()) ? pet : p);
            CompletableFuture.runAsync(() -> notificationServer.notify(pet));
            return true;
        }
        throw new PetNotFoundException("Pet not found for id: " + id);
    }

    public Map<PetType, Long> countByType(){
        return cache.stream()
                .collect(Collectors.groupingBy(Pet::getType, Collectors.counting()));
    }

    public Optional<PetResponseDTO> findOldest() {
        return cache.stream()
                .max(Comparator.comparingDouble(Pet::getAge))
                .map(PetResponseDTO::fromPet);
    }

    public Map<PetType, Double> averageWeightByType() {
        return cache.stream()
                .collect(Collectors.groupingBy(Pet::getType, Collectors.averagingDouble(Pet::getWeight)));
    }
}