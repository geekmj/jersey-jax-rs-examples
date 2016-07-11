package org.geekmj.resource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.geekmj.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public EmployeeResource(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@POST
	public String createEmployee(final Employee employee) {

		String sql = "insert into EMPLOYEE values(DEFAULT, :name, :address, :createdOn, :modifiedOn)";

		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(employee);

		this.namedParameterJdbcTemplate.update(sql, namedParameters);

		return "{\"status\": \"Record Added Successfully\"}";
	}

	@GET
	@Path("/{employeeId}")
	public Employee getEmployee(@PathParam("employeeId") Integer employeeId) {
		String sql = "select * from EMPLOYEE where id=:employeeId";
		SqlParameterSource namedParameters = new MapSqlParameterSource("employeeId", employeeId);
		return namedParameterJdbcTemplate.query(sql, namedParameters, new EmployeeMapper()).get(0);
	}

	@PUT
	@Path("/{employeeId}")
	public Employee editEmployee(final Employee employee, @PathParam("employeeId") Integer employeeId) {

		String sql = "update EMPLOYEE set name=:name, address=:address where id=:id";

		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(employee);

		this.namedParameterJdbcTemplate.update(sql, namedParameters);

		sql = "select * from EMPLOYEE where id=:employeeId";
		SqlParameterSource namedParameters2 = new MapSqlParameterSource("employeeId", employeeId);
		return namedParameterJdbcTemplate.query(sql, namedParameters2, new EmployeeMapper()).get(0);
	}

	@GET
	public List<Employee> ListEmployees() {
		String sql = "select * from EMPLOYEE";

		return namedParameterJdbcTemplate.query(sql, new EmployeeMapper());
	}

	@DELETE
	@Path("/{employeeId}")
	public String deleteEmployees(@PathParam("employeeId") Integer employeeId) {
		String sql = "delete from EMPLOYEE where id=:employeeId";
		SqlParameterSource namedParameters = new MapSqlParameterSource("employeeId", employeeId);
		this.namedParameterJdbcTemplate.update(sql, namedParameters);
		return "{\"status\": \"Record Deleted Successfully\"}";
	}

	private static final class EmployeeMapper implements RowMapper<Employee> {

		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee employee = new Employee();
			employee.setId(rs.getInt("id"));
			employee.setName(rs.getString("name"));
			employee.setAddress(rs.getString("address"));
			employee.setCreatedOn(rs.getDate("created_on"));
			employee.setModifiedOn(rs.getDate("modified_on"));
			return employee;
		}
	}
}
