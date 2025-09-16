package com.youpi.youpi.controller;

import com.youpi.youpi.entity.SmartSaver;
import com.youpi.youpi.service.SmartSaverService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/smart-saver")
public class SmartSaverController {

    private final SmartSaverService smartSaverService;

    public SmartSaverController(SmartSaverService smartSaverService) {
        this.smartSaverService = smartSaverService;
    }

    @PostMapping("/create")
    public SmartSaver createEmi(@RequestBody SmartSaver emi) {
        return smartSaverService.createEmi(emi);
    }

    @GetMapping
    public List<SmartSaver> getAllEmis() {
        return smartSaverService.getAllEmis();
    }

    @GetMapping("/{id}")
    public SmartSaver getEmiById(@PathVariable Long id) {
        return smartSaverService.getEmiById(id)
                .orElseThrow(() -> new RuntimeException("EMI not found with id " + id));
    }

    @PutMapping("/{id}")
    public SmartSaver updateEmi(@PathVariable Long id, @RequestBody SmartSaver emi) {
        return smartSaverService.updateEmi(id, emi);
    }

    @DeleteMapping("/{id}")
    public String deleteEmi(@PathVariable Long id) {
        smartSaverService.deleteEmi(id);
        return "EMI deleted successfully with id " + id;
    }
}
