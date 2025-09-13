package com.youpi.youpi.controller;

import com.youpi.youpi.entity.Operators;
import com.youpi.youpi.service.OperatorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operators")
public class OperatorsController {

    @Autowired
    private OperatorsService operatorsService;

    // Create operator
    @PostMapping("/create")
    public Operators createOperator(@RequestBody Operators operators) {
        return operatorsService.createOperator(operators);
    }

    // Get all operators
    @GetMapping("/all")
    public List<Operators> getAllOperators() {
        return operatorsService.getAllOperators();
    }

    // Get operator by ID
    @GetMapping("/{id}")
    public Operators getOperatorById(@PathVariable Long id) {
        return operatorsService.getOperatorById(id);
    }

    // Update operator
    @PutMapping("/{id}")
    public Operators updateOperator(@PathVariable Long id, @RequestBody Operators operators) {
        return operatorsService.updateOperator(id, operators);
    }

    // Delete operator (soft delete -> set deletedAt)
    @DeleteMapping("/{id}")
    public String deleteOperator(@PathVariable Long id) {
        operatorsService.deleteOperator(id);
        return "Operator with ID " + id + " deleted successfully.";
    }
}
