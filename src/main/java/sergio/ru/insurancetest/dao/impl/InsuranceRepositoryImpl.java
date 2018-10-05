package sergio.ru.insurancetest.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import sergio.ru.insurancetest.controller.InsuranceController;
import sergio.ru.insurancetest.dao.InsuranceRepository;
import sergio.ru.insurancetest.dto.ContractDto;
import sergio.ru.insurancetest.model.Contract;
import sergio.ru.insurancetest.model.ContractType;
import sergio.ru.insurancetest.model.Vehicle;
import sergio.ru.insurancetest.utils.ContractConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class InsuranceRepositoryImpl implements InsuranceRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsuranceRepositoryImpl.class);

    private static final String ALL_QUERY = "SELECT Contract.id, Contract.serie, Contract.number, Contract.sign_date, Contract.open_date, Contract.expiration_date, " +
            "Contract.nds_sum, Contract.sum_with_nds, Contract.vehicle_number, Contract.note, ContractType.name " +
            "FROM Contract, ContractType WHERE Contract.contract_type_id = ContractType.id";

    private static final String REMOVE_QUERY = "DELETE FROM Contract WHERE id=:id";

    private static final String INSERT_QUERY = "INSERT INTO Contract(contract_type_id, serie, number, sign_date, open_date, expiration_date, nds_sum, sum_with_nds, vehicle_number, note) "
            + "SELECT ct.id, :serie, :number, :sign_date, :open_date, :expiration_date, :nds_sum, :sum_with_nds, :vehicle_number, :note FROM ContractType ct WHERE ct.name = :name";

    private static final String UPDATE_QUERY = "UPDATE Contract SET serie=:serie, number=:number, sign_date=:sign_date, "
            + "open_date=:open_date, expiration_date=:expiration_date, nds_sum=:nds_sum, sum_with_nds=:sum_with_nds, "
            + "contract_type_id=(SELECT ct.id FROM ContractType ct WHERE ct.name =:name), "
            + "vehicle_number=:vehicle_number, note=:note WHERE id=:id";

    private static final String TYPES_QUERY = "SELECT id, name FROM ContractType";

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public InsuranceRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Contract findById(Integer id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        String sql = "SELECT * FROM Contract " +
                     "INNER JOIN ContractType ON (Contract.contract_type_id = ContractType.id) " +
                     "WHERE Contract.id=:id";

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
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("number", number);
        String sql = "SELECT * FROM Vehicle WHERE number=:number";

        Vehicle result = null;
        try {
            result = namedParameterJdbcTemplate
                    .queryForObject(sql, params, new VehicleMapper());
        } catch (EmptyResultDataAccessException e) {
            // do nothing, return null
        }

        return result;
    }

    @Override
    public List<Contract> findAllContracts() {
        String sql = ALL_QUERY;
        List<Contract> result = namedParameterJdbcTemplate.query(sql, new ContractMapper());
        return result;
    }

    @Override
    public void save(Contract contract) {
        LOGGER.info("Repository save() contract:{}", contract);
        String sql = INSERT_QUERY;
        try {
            namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(contract));
        } catch (DataAccessException e) {
            LOGGER.error("Error during saving date: {}", e.getMessage());
        }

    }

    @Override
    public void update(Contract contract) {
        String sql = UPDATE_QUERY;

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(contract));
    }

    @Override
    public void remove(Integer id) {
        String sql = REMOVE_QUERY;
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }

    @Override
    public List<String> getAllContractTypes() {
        String sql = TYPES_QUERY;
        List<ContractType> types = namedParameterJdbcTemplate.query(sql, new ContractTypeMapper());
        List<String> result = types.stream()
                .map(contractType -> contractType.getName())
                .collect(Collectors.toList());
        return result;
    }

    private SqlParameterSource getSqlParameterByModel(Contract contract) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", contract.getId());
        paramSource.addValue("serie", contract.getSerie());
        paramSource.addValue("number", contract.getNumber());
        paramSource.addValue("sign_date", contract.getSignDate());
        paramSource.addValue("open_date", contract.getOpenDate());
        paramSource.addValue("expiration_date", contract.getExpirationDate());
        paramSource.addValue("nds_sum", contract.getNdsSum());
        paramSource.addValue("sum_with_nds", contract.getSumWithNds());
        paramSource.addValue("vehicle_number", contract.getVehicleNumber());
        paramSource.addValue("note", contract.getNote());
        paramSource.addValue("name", contract.getType());

        return paramSource;
    }

    private static final class ContractMapper implements RowMapper<Contract> {

        public Contract mapRow(ResultSet rs, int rowNum) throws SQLException {
            Contract contract = new Contract();
            contract.setId(rs.getInt("id"));
            contract.setType(rs.getString("name"));
            contract.setExpirationDate(rs.getDate("expiration_date").toLocalDate());
            contract.setSignDate(rs.getDate("sign_date").toLocalDate());
            contract.setOpenDate(rs.getDate("open_date").toLocalDate());
            contract.setNdsSum(rs.getDouble("nds_sum"));
            contract.setSumWithNds(rs.getDouble("sum_with_nds"));
            contract.setNumber(rs.getString("number"));
            contract.setSerie(rs.getString("serie"));
            contract.setNote(rs.getString("note"));
            contract.setVehicleNumber(rs.getString("vehicle_number"));
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
