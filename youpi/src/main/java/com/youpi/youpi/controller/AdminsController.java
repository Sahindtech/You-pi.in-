package com.youpi.youpi.controller;

import com.youpi.youpi.entity.Admins;
import com.youpi.youpi.service.AdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminsController {

    @Autowired
    private AdminsService adminsService;

    // 🔹 Create New Admin
    @PostMapping ("/create")
    public Admins createAdmin(@RequestBody Admins admin) {
        return adminsService.createAdmin(admin);
    }

    // 🔹 Get Admin by ID
    @GetMapping("/{id}")
    public Admins getAdminById(@PathVariable Long id) {
        return adminsService.getAdminById(id);
    }

    // 🔹 Get All Admins
    @GetMapping
    public List<Admins> getAllAdmins() {
        return adminsService.getAllAdmins();
    }

    // 🔹 Update Admin
    @PutMapping("/{id}")
    public Admins updateAdmin(@PathVariable Long id, @RequestBody Admins updatedAdmin) {
        return adminsService.updateAdmin(id, updatedAdmin);
    }

    // 🔹 Delete Admin
    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminsService.deleteAdmin(id);
    }

    // 🔹 Admin Login
    @PostMapping("/login")
    public Admins login(@RequestParam String username, @RequestParam String password) {
        return adminsService.login(username, password);
    }
}
