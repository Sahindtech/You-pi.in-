package com.youpi.youpi.service;

import com.youpi.youpi.entity.Admins;
import com.youpi.youpi.repository.AdminsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminsService {

    @Autowired
    private AdminsRepository adminsRepository;

    // 🔹 Create New Admin
    public Admins createAdmin(Admins admin) {
        return adminsRepository.save(admin);
    }

    // 🔹 Get Admin by ID
    public Admins getAdminById(Long id) {
        return adminsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
    }

    // 🔹 Get All Admins
    public List<Admins> getAllAdmins() {
        return adminsRepository.findAll();
    }

    // 🔹 Update Admin
    public Admins updateAdmin(Long id, Admins updatedAdmin) {
        Admins existingAdmin = adminsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));

        existingAdmin.setUsername(updatedAdmin.getUsername());
        existingAdmin.setPassword(updatedAdmin.getPassword()); // ⚠️ hash karna better hai
        existingAdmin.setEmail(updatedAdmin.getEmail());
        existingAdmin.setRole(updatedAdmin.getRole());

        return adminsRepository.save(existingAdmin);
    }

    // 🔹 Delete Admin
    public void deleteAdmin(Long id) {
        adminsRepository.deleteById(id);
    }

    // 🔹 Simple Login check (username + password)
    public Admins login(String username, String password) {
        Admins admin = adminsRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        if (!admin.getPassword().equals(password)) { // ⚠️ hashing required in real apps
            throw new RuntimeException("Invalid password");
        }

        return admin;
    }
}
