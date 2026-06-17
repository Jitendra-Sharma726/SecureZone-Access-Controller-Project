package controller;

import model.Employee;
import model.SecureZone;
import service.AccessService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccessController {

    private final AccessService accessService;

    public AccessController(AccessService accessService) {
        this.accessService = accessService;
    }

    // Add Employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return accessService.createEmployee(employee);
    }

    // Add Zone
    @PostMapping("/zones")
    public SecureZone addZone(@RequestBody SecureZone zone) {
        return accessService.createZone(zone);
    }

    // Grant Access
    @PostMapping("/employees/{employeeId}/zones/{zoneId}")
    public Employee grantAccess(@PathVariable Long employeeId,
                                @PathVariable Long zoneId) {
        return accessService.grantZoneAccess(employeeId, zoneId);
    }

    // Remove Employee
    @DeleteMapping("/employees/{employeeId}")
    public String removeEmployee(@PathVariable Long employeeId) {
        accessService.revokeEmployee(employeeId);
        return "Employee deleted successfully";
    }
}
