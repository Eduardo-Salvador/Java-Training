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
        log.info("Saving pet: {}", entity.getName());
        String query = "INSERT INTO petadoption.pets (name, type, sex, age, weight, breed, address, status) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = savePrepareStatement(conn, query, entity)){
            ps.executeUpdate();
            ResultSet key = ps.getGeneratedKeys();
            if (key.next()){
                entity.setId(key.getLong(1));
            }
        } catch (SQLException e){
            throw new DatabaseConnectionException("Error connection: " + e);
        }
    }

    @Override
    public Optional<Pet> findById(Long aLong) throws DatabaseConnectionException  {
        log.info("Searching ID: {}", aLong);
        String query = "SELECT * FROM petadoption.pets "
                        + "WHERE (ID = ?);";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = findByIdPrepareStatement(conn, query, aLong);
            ResultSet set = ps.executeQuery();){
            return Optional.of(Pet.PetBuilder.aPet()
                        .withName(set.getString(1))
                        .withType(PetType.valueOf(set.getString(2)))
                        .withSex(PetSex.valueOf(set.getString(3)))
                        .withAge(set.getDouble(4))
                        .withWeight(set.getDouble(5))
                        .withBreed(set.getString(6))
                        .withAddress(set.getString(7))
                        .withStatus(PetStatus.valueOf(set.getString(8)))
                        .build());
        } catch (SQLException e){
            throw new DatabaseConnectionException("Error connection: " + e);
        } catch (PetValidationException e) {
            log.error(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Pet> findByType(PetType type) throws DatabaseConnectionException  {
        log.info("Searching by type: {}", type);
        String query = "SELECT * FROM petadoption.pets "
                        + "WHERE (type = ?);";
        List<Pet> petList = new ArrayList<>();
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = findByTypePrepareStatement(conn, query, type);
            ResultSet set = ps.executeQuery();){
            while (set.next()){
                petList.add(Pet.PetBuilder.aPet()
                        .withName(set.getString(1))
                        .withType(PetType.valueOf(set.getString(2)))
                        .withSex(PetSex.valueOf(set.getString(3)))
                        .withAge(set.getDouble(4))
                        .withWeight(set.getDouble(5))
                        .withBreed(set.getString(6))
                        .withAddress(set.getString(7))
                        .withStatus(PetStatus.valueOf(set.getString(8)))
                        .build());
            }
        } catch (SQLException e){
            throw new DatabaseConnectionException("Error connection: " + e);
        } catch (PetValidationException e){
            log.error(e);
        }
        return petList;
    }

    @Override
    public List<Pet> findAll() throws DatabaseConnectionException {
        log.info("Searching all:");
        String query = "SELECT * FROM petadoption.pets;";
        List<Pet> petList = new ArrayList<>();
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = findAllPrepareStatement(conn, query);
            ResultSet set = ps.executeQuery();){
            while (set.next()){
                petList.add(Pet.PetBuilder.aPet()
                        .withName(set.getString(1))
                        .withType(PetType.valueOf(set.getString(2)))
                        .withSex(PetSex.valueOf(set.getString(3)))
                        .withAge(set.getDouble(4))
                        .withWeight(set.getDouble(5))
                        .withBreed(set.getString(6))
                        .withAddress(set.getString(7))
                        .withStatus(PetStatus.valueOf(set.getString(8)))
                        .build());
            }
        } catch (SQLException e){
            throw new DatabaseConnectionException("Error connection: " + e);
        } catch (PetValidationException e){
            log.error(e);
        }
        return petList;
    }

    @Override
    public void update(Pet entity) throws DatabaseConnectionException  {
        log.info("Updating name: {}", entity.getName());
        String query = "UPDATE petadoption.pets "
                        + "SET name = ?, type = ?, sex = ?, age = ?, weight = ?, breed = ?, address = ?, status = ?";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = updatePrepareStatement(conn, query, entity)){

        } catch (SQLException e){
            throw new DatabaseConnectionException("Error connection: " + e);
        }
    }

    @Override
    public void delete(Long aLong) {

    }

    private PreparedStatement savePrepareStatement(Connection conn, String query, Pet entity) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(query);
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
        return ps;
    }
}