package com.youpi.youpi.service;

import com.youpi.youpi.entity.Operators;
import com.youpi.youpi.repository.OperatorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OperatorsService {

    @Autowired
    private OperatorsRepository operatorsRepository;

    // Create operator
    public Operators createOperator(Operators operators) {
        operators.setCreatedAt(LocalDateTime.now());
        operators.setUpdatedAt(LocalDateTime.now());
        return operatorsRepository.save(operators);
    }

    // Get all operators
    public List<Operators> getAllOperators() {
        return operatorsRepository.findAll();
    }

    // Get operator by ID
    public Operators getOperatorById(Long id) {
        Optional<Operators> operator = operatorsRepository.findById(id);
        return operator.orElse(null);
    }

    // Update operator
    public Operators updateOperator(Long id, Operators updatedData) {
        return operatorsRepository.findById(id).map(operator -> {
            operator.setName(updatedData.getName());
            operator.setCode(updatedData.getCode());
            operator.setCircleCode(updatedData.getCircleCode());
            operator.setType(updatedData.getType());
            operator.setCategory(updatedData.getCategory());
            operator.setIsActive(updatedData.getIsActive());
            operator.setCommissionRate(updatedData.getCommissionRate());
            operator.setDailyLimit(updatedData.getDailyLimit());
            operator.setWalletEnabled(updatedData.getWalletEnabled());
            operator.setProviderName(updatedData.getProviderName());
            operator.setUpdatedAt(LocalDateTime.now());
            return operatorsRepository.save(operator);
        }).orElse(null);
    }

    // Soft delete operator
    public void deleteOperator(Long id) {
        operatorsRepository.findById(id).ifPresent(operator -> {
            operator.setDeletedAt(LocalDateTime.now());
            operatorsRepository.save(operator);
        });
    }
}
