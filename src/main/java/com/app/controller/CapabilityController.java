package com.app.controller;

import com.app.entity.Capability;
import com.app.repository.CapabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/capabilities")
public class CapabilityController {
    @Autowired
    private CapabilityRepository capabilityRepository;

    @GetMapping("/{capabilityId}")
    public ResponseEntity<Capability> getCapabilityById(@PathVariable Long capabilityId) {
        Optional<Capability> capability = capabilityRepository.findById(capabilityId);
        return capability.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Capability createCapability(@RequestBody Capability capability) {
        return capabilityRepository.save(capability);
    }

    @PutMapping("/{capabilityId}")
    public ResponseEntity<Capability> updateCapability(@PathVariable Long capabilityId, @RequestBody Capability capabilityDetails) {
        Optional<Capability> optionalCapability = capabilityRepository.findById(capabilityId);
        if (optionalCapability.isPresent()) {
            Capability capability = optionalCapability.get();
            capability.setCapabilityName(capabilityDetails.getCapabilityName());
            capability.setCapabilityDescription(capabilityDetails.getCapabilityDescription());
            capability.setCapabilityType(capabilityDetails.getCapabilityType());
            return ResponseEntity.ok(capabilityRepository.save(capability));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{capabilityId}")
    public ResponseEntity<Void> deleteCapability(@PathVariable Long capabilityId) {
        if (capabilityRepository.existsById(capabilityId)) {
            capabilityRepository.deleteById(capabilityId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/all")
    public Page<Capability> getAllCapabilities(Pageable pageable) {
        return capabilityRepository.findAll(pageable);
    }

}


