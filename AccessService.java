package service;

import model.Employee;
import model.SecureZone;
import repository.EmployeeRepository;
import repository.SecureZoneRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccessService {

    private final EmployeeRepository employeeRepository;
    private final SecureZoneRepository secureZoneRepository;

    public AccessService(EmployeeRepository employeeRepository,
                         SecureZoneRepository secureZoneRepository) {
        this.employeeRepository = employeeRepository;
        this.secureZoneRepository = secureZoneRepository;
    }

    // Create Employee
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Create Zone
    public SecureZone createZone(SecureZone zone) {
        return secureZoneRepository.save(zone);
    }

    // Grant Access
    public Employee grantZoneAccess(Long employeeId, Long zoneId) {

        Optional<Employee> empOpt = employeeRepository.findById(employeeId);
        Optional<SecureZone> zoneOpt = secureZoneRepository.findById(zoneId);

        if (empOpt.isEmpty() || zoneOpt.isEmpty()) {
            throw new RuntimeException("Employee or Zone not found");
        }

        Employee employee = empOpt.get();
        SecureZone zone = zoneOpt.get();

        employee.addZone(zone);

        return employeeRepository.save(employee);
    }

    // Remove Employee
    public void revokeEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
