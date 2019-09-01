package com.example.sorceforresttemplate.sourceemployee.Controller;

import com.example.sorceforresttemplate.sourceemployee.Model.Employee;
import com.example.sorceforresttemplate.sourceemployee.Model.Role;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class EmployeeController {

    private static Map<Long, Employee> repository = new HashMap<>();
    static {
        repository.put(1L, new Employee(1L, "Oleg", Role.ADMIN));
        repository.put(2L, new Employee(2L, "Vasya", Role.WORKER));
        repository.put(3L, new Employee(3L, "Valera", Role.MANEGER));
    }
    @GetMapping("/employee")
    public List<Employee> getAllEmployee() {
        return new ArrayList<>(repository.values());
    }

    @GetMapping("employee/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return repository.getOrDefault(id, null);
    }

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee) {
        repository.put(employee.getId(), employee);
        return repository.get(employee.getId());
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable Long id,
                                   @RequestBody Employee employee) {
        if (repository.containsKey(id)) {
            Employee updatedEmployee = new Employee();
            updatedEmployee.setId(id);
            updatedEmployee.setName(employee.getName());
            updatedEmployee.setRole(employee.getRole());
            repository.put(updatedEmployee.getId(), updatedEmployee);
            return updatedEmployee;
        } else {
            return null;
        }

    }
    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        if(repository.remove(id) == null) throw new NoSuchElementException("Employee with this id does not exist");
    }
}
