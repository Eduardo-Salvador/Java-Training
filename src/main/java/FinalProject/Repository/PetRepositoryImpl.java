package FinalProject.Repository;
import FinalProject.Domain.*;
import FinalProject.Exception.*;
import java.sql.*;
import java.util.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PetRepositoryImpl implements PetRepository<Pet, Long>{

    @Override
    public void save(Pet entity) throws DatabaseConnectionException  {
        log.debug("SQL: INSERT pet name={}, type={}, sex={}", entity.getName(), entity.getType(), entity.getSex());
        String query = "INSERT INTO petadoption.pets (name, type, sex, age, weight, breed, address, status) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = savePrepareStatement(conn, query, entity)) {
            ps.executeUpdate();
            try (ResultSet key = ps.getGeneratedKeys()) {
                if (key.next()) {
                    entity.setId(key.getLong(1));
                }
            }
        } catch (SQLException e){
            log.error("Error saving pet name={}: {}", entity.getName(), e.getMessage());
            throw new DatabaseConnectionException("Error connection: " + e);
        }
    }

    @Override
    public Optional<Pet> findById(Long id) throws DatabaseConnectionException  {
        log.debug("SQL: SELECT pet WHERE id={}", id);
        String query = "SELECT * FROM petadoption.pets "
                        + "WHERE (ID = ?);";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = findByIdPrepareStatement(conn, query, id);
            ResultSet set = ps.executeQuery();){
            if (set.next()) {
                return Optional.of(Pet.PetBuilder.aPet()
                        .withId(set.getLong("id"))
                        .withName(set.getString("name"))
                        .withType(PetType.valueOf(set.getString("type")))
                        .withSex(PetSex.valueOf(set.getString("sex")))
                        .withAge(set.getDouble("age"))
                        .withWeight(set.getDouble("weight"))
                        .withBreed(set.getString("breed"))
                        .withAddress(set.getString("address"))
                        .withStatus(PetStatus.valueOf(set.getString("status")))
                        .build());
            }
        } catch (SQLException e){
            log.error("Error finding pet id={}: {}", id, e.getMessage());
            throw new DatabaseConnectionException("Error connection: " + e);
        } catch (PetValidationException e) {
            log.error("Error building pet from ResultSet id={}: {}", id, e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Pet> findByType(PetType type) throws DatabaseConnectionException  {
        log.debug("SQL: SELECT pets WHERE type={}", type);
        String query = "SELECT * FROM petadoption.pets "
                        + "WHERE (type = ?);";
        List<Pet> petList = new ArrayList<>();
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = findByTypePrepareStatement(conn, query, type);
            ResultSet set = ps.executeQuery();){
            while (set.next()){
                petList.add(Pet.PetBuilder.aPet()
                        .withId(set.getLong("id"))
                        .withName(set.getString("name"))
                        .withType(PetType.valueOf(set.getString("type")))
                        .withSex(PetSex.valueOf(set.getString("sex")))
                        .withAge(set.getDouble("age"))
                        .withWeight(set.getDouble("weight"))
                        .withBreed(set.getString("breed"))
                        .withAddress(set.getString("address"))
                        .withStatus(PetStatus.valueOf(set.getString("status")))
                        .build());
            }
            log.debug("SQL: SELECT successful, found {} pets of type={}", petList.size(), type);
        } catch (SQLException e){
            log.error("Error finding pets by type={}: {}", type, e.getMessage());
            throw new DatabaseConnectionException("Error connection: " + e);
        } catch (PetValidationException e){
            log.error("Error building pet from ResultSet type={}: {}", type, e.getMessage());
        }
        return petList;
    }

    @Override
    public List<Pet> findAll() throws DatabaseConnectionException {
        log.debug("SQL: SELECT all pets");
        String query = "SELECT * FROM petadoption.pets;";
        List<Pet> petList = new ArrayList<>();
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = findAllPrepareStatement(conn, query);
            ResultSet set = ps.executeQuery();){
            while (set.next()){
                petList.add(Pet.PetBuilder.aPet()
                        .withId(set.getLong("id"))
                        .withName(set.getString("name"))
                        .withType(PetType.valueOf(set.getString("type")))
                        .withSex(PetSex.valueOf(set.getString("sex")))
                        .withAge(set.getDouble("age"))
                        .withWeight(set.getDouble("weight"))
                        .withBreed(set.getString("breed"))
                        .withAddress(set.getString("address"))
                        .withStatus(PetStatus.valueOf(set.getString("status")))
                        .build());
            }
            log.debug("SQL: SELECT successful, found {} pets", petList.size());
        } catch (SQLException e){
            log.error("Error finding all pets: {}", e.getMessage());
            throw new DatabaseConnectionException("Error connection: " + e);
        } catch (PetValidationException e){
            log.error("Error building pet from ResultSet: {}", e.getMessage());
        }
        return petList;
    }

    @Override
    public void update(Pet entity) throws DatabaseConnectionException  {
        log.debug("SQL: UPDATE pet id={}, name={}", entity.getId(), entity.getName());
        String query = "UPDATE petadoption.pets "
                        + "SET name = ?, type = ?, sex = ?, age = ?, weight = ?, breed = ?, address = ?, status = ?"
                        + "WHERE id = ?";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = updatePrepareStatement(conn, query, entity)){
            ps.executeUpdate();
            log.debug("SQL: UPDATE successful, pet id={}", entity.getId());
        } catch (SQLException e){
            log.error("Error updating pet id={}: {}", entity.getId(), e.getMessage());
            throw new DatabaseConnectionException("Error connection: " + e);
        }
    }

    @Override
    public void delete(Long id) throws DatabaseConnectionException {
        log.debug("SQL: DELETE pet WHERE id={}", id);
        String query = "DELETE FROM petadoption.pets "
                        + "WHERE id = ?";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = deletePrepareStatement(conn, query, id)){
            ps.execute();
            log.debug("SQL: DELETE successful, pet id={}", id);
        } catch (SQLException e){
            log.error("Error deleting pet id={}: {}", id, e.getMessage());
            throw new DatabaseConnectionException("Error connection: " + e);
        }
    }

    private PreparedStatement savePrepareStatement(Connection conn, String query, Pet entity) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, entity.getName());
        ps.setString(2, entity.getType().toString());
        ps.setString(3, entity.getSex().toString());
        ps.setDouble(4, entity.getAge());
        ps.setDouble(5, entity.getWeight());
        ps.setString(6, entity.getBreed());
        ps.setString(7, entity.getAddress());
        ps.setString(8, entity.getStatus().toString());
        return ps;
    }

    private PreparedStatement findByIdPrepareStatement(Connection conn, String query, Long id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setLong(1, id);
        return ps;
    }

    private PreparedStatement findByTypePrepareStatement(Connection conn, String query, PetType type) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, type.toString());
        return ps;
    }

    private PreparedStatement findAllPrepareStatement(Connection conn, String query) throws SQLException {
        return conn.prepareStatement(query);
    }

    private PreparedStatement updatePrepareStatement(Connection conn, String query, Pet entity) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, entity.getName());
        ps.setString(2, entity.getType().toString());
        ps.setString(3, entity.getSex().toString());
        ps.setDouble(4, entity.getAge());
        ps.setDouble(5, entity.getWeight());
        ps.setString(6, entity.getBreed());
        ps.setString(7, entity.getAddress());
        ps.setString(8, entity.getStatus().toString());
        ps.setLong(9, entity.getId());
        return ps;
    }

    private PreparedStatement deletePrepareStatement(Connection conn, String query, Long id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setLong(1, id);
        return ps;
    }
}