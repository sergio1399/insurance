package sergio.ru.insurancetest.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import sergio.ru.insurancetest.dao.InsuranceRepository;
import sergio.ru.insurancetest.model.Contract;
import sergio.ru.insurancetest.model.ContractType;
import sergio.ru.insurancetest.model.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InsuranceRepositoryImpl implements InsuranceRepository {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String ALL_QUERY = "SELECT Contract.id, Contract.serie, Contract.number, Contract.sign_date, Contract.open_date, Contract.expiration_date, " +
                                            "Contract.nds_sum, Contract.sum_with_nds, Contract.vehicle_number, Contract.note, ContractType.name" +
                                            " FROM Contract, ContractType WHERE Contract.contract_type_id = ContractType.id";

    public InsuranceRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Contract findById(Integer id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        String sql = "SELECT * FROM Contract WHERE id=:id";

        Contract result = null;
        try {
            result = namedParameterJdbcTemplate
                    .queryForObject(sql, params, new ContractMapper());
        } catch (EmptyResultDataAccessException e) {
            // do nothing, return null
        }

        return result;
    }

    @Override
    public ContractType findType(Integer id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        String sql = "SELECT * FROM ContractType WHERE id=:id";

        ContractType result = null;
        try {
            result = namedParameterJdbcTemplate
                    .queryForObject(sql, params, new ContractTypeMapper());
        } catch (EmptyResultDataAccessException e) {
            // do nothing, return null
        }

        return result;
    }

    @Override
    public Vehicle findVehicle(String number) {
        return null;
    }

    @Override
    public List<Contract> findAllContracts() {
        String sql = ALL_QUERY;
        List<Contract> result = namedParameterJdbcTemplate.query(sql, new ContractMapper());
        return result;
    }

    @Override
    public void save(Contract contract) {

    }

    @Override
    public void update(Contract contract) {

    }

    @Override
    public void remove(Integer id) {

    }

    private static final class ContractMapper implements RowMapper<Contract> {

        public Contract mapRow(ResultSet rs, int rowNum) throws SQLException {
            Contract contract = new Contract();
            contract.setId(rs.getInt("id"));
            contract.setType(rs.getString("name"));
            contract.setExpirationDate(rs.getDate("expiration_date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            contract.setSignDate(rs.getDate("sign_date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            contract.setOpenDate(rs.getDate("open_date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            contract.setNdsSum(rs.getDouble("nds_sum"));
            contract.setSumWithNds(rs.getDouble("sum_with_nds"));
            contract.setNumber(rs.getString("number"));
            contract.setSerie(rs.getString("serie"));
            contract.setNote(rs.getString("note"));
            contract.setVehicleNumber("vehicle_number");
            return contract;
        }
    }

    private static final class ContractTypeMapper implements RowMapper<ContractType> {

        public ContractType mapRow(ResultSet rs, int rowNum) throws SQLException {
            ContractType contractType = new ContractType();
            contractType.setId(rs.getInt("id"));
            contractType.setName(rs.getString("name"));
            return contractType;
        }
    }

    private static final class VehicleMapper implements RowMapper<Vehicle> {

        public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
            Vehicle vehicle = new Vehicle();
            vehicle.setNumber(rs.getString("number"));
            vehicle.setBrand(rs.getString("brand"));
            vehicle.setModel(rs.getString("model"));
            vehicle.setYearOfCreation(rs.getInt("year_of_creation"));
            return vehicle;
        }
    }
}
