package com.aixtor.training.liferay.employee.headless.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author shani.patel
 * @generated
 */
@Generated("")
@GraphQLName("EmployeeDetaByDepartmentResponse")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "EmployeeDetaByDepartmentResponse")
public class EmployeeDetaByDepartmentResponse implements Serializable {

	public static EmployeeDetaByDepartmentResponse toDTO(String json) {
		return ObjectMapperUtil.readValue(
			EmployeeDetaByDepartmentResponse.class, json);
	}

	public static EmployeeDetaByDepartmentResponse unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(
			EmployeeDetaByDepartmentResponse.class, json);
	}

	@Schema(description = "The Department Details")
	@Valid
	public Department[] getDepartments() {
		return departments;
	}

	public void setDepartments(Department[] departments) {
		this.departments = departments;
	}

	@JsonIgnore
	public void setDepartments(
		UnsafeSupplier<Department[], Exception> departmentsUnsafeSupplier) {

		try {
			departments = departmentsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The Department Details")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Department[] departments;

	@Schema(description = "The employee Details")
	@Valid
	public Employee[] getEmployees() {
		return employees;
	}

	public void setEmployees(Employee[] employees) {
		this.employees = employees;
	}

	@JsonIgnore
	public void setEmployees(
		UnsafeSupplier<Employee[], Exception> employeesUnsafeSupplier) {

		try {
			employees = employeesUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The employee Details")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Employee[] employees;

	@Schema(description = "The status code of the status")
	@Valid
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@JsonIgnore
	public void setStatus(
		UnsafeSupplier<Status, Exception> statusUnsafeSupplier) {

		try {
			status = statusUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The status code of the status")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Status status;

	@Schema(description = "total employee details")
	public Long getTotalEmployee() {
		return totalEmployee;
	}

	public void setTotalEmployee(Long totalEmployee) {
		this.totalEmployee = totalEmployee;
	}

	@JsonIgnore
	public void setTotalEmployee(
		UnsafeSupplier<Long, Exception> totalEmployeeUnsafeSupplier) {

		try {
			totalEmployee = totalEmployeeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "total employee details")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long totalEmployee;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EmployeeDetaByDepartmentResponse)) {
			return false;
		}

		EmployeeDetaByDepartmentResponse employeeDetaByDepartmentResponse =
			(EmployeeDetaByDepartmentResponse)object;

		return Objects.equals(
			toString(), employeeDetaByDepartmentResponse.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (departments != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"departments\": ");

			sb.append("[");

			for (int i = 0; i < departments.length; i++) {
				sb.append(String.valueOf(departments[i]));

				if ((i + 1) < departments.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (employees != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"employees\": ");

			sb.append("[");

			for (int i = 0; i < employees.length; i++) {
				sb.append(String.valueOf(employees[i]));

				if ((i + 1) < employees.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (status != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"status\": ");

			sb.append(String.valueOf(status));
		}

		if (totalEmployee != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"totalEmployee\": ");

			sb.append(totalEmployee);
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.aixtor.training.liferay.employee.headless.dto.v1_0.EmployeeDetaByDepartmentResponse",
		name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		return StringUtil.replace(
			String.valueOf(object), _JSON_ESCAPE_STRINGS[0],
			_JSON_ESCAPE_STRINGS[1]);
	}

	private static boolean _isArray(Object value) {
		if (value == null) {
			return false;
		}

		Class<?> clazz = value.getClass();

		return clazz.isArray();
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(_escape(entry.getKey()));
			sb.append("\": ");

			Object value = entry.getValue();

			if (_isArray(value)) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(value));
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static final String[][] _JSON_ESCAPE_STRINGS = {
		{"\\", "\"", "\b", "\f", "\n", "\r", "\t"},
		{"\\\\", "\\\"", "\\b", "\\f", "\\n", "\\r", "\\t"}
	};

}