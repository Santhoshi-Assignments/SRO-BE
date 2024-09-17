package com.app.controller;
import com.app.entity.TechTeam;
import com.app.repository.TechTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/techTeam")
public class TechTeamController {
    @Autowired
    private TechTeamRepository techTeamRepository;
    @GetMapping("/{techTeamId}")
    public ResponseEntity<TechTeam> getTechTeamById(@PathVariable int techTeamId) {
        Optional<TechTeam> techTeam = techTeamRepository.findById(techTeamId);
        return techTeam.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TechTeam createTechTeam(@RequestBody TechTeam techTeam) {
        return techTeamRepository.save(techTeam);
    }

    @GetMapping("/all")
    public Page<TechTeam> getAllTechTeam(Pageable pageable) {
        return techTeamRepository.findAll(pageable);
    }

    @PutMapping("/{techTeamId}")
    public ResponseEntity<TechTeam> updateTechTeam(@PathVariable int techTeamId, @RequestBody TechTeam techTeamDetails) {
        Optional<TechTeam> optionalTechTeam = techTeamRepository.findById(techTeamId);
        if (optionalTechTeam.isPresent()) {
            TechTeam techTeam = optionalTechTeam.get();
            techTeam.setTechTeamName(techTeamDetails.getTechTeamName());
            techTeam.setTechTeamTPM(techTeamDetails.getTechTeamTPM());
            techTeam.setTechTeamManager(techTeamDetails.getTechTeamManager());
            techTeam.setTechTeamDescription(techTeamDetails.getTechTeamDescription());
            return ResponseEntity.ok(techTeamRepository.save(techTeam));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{techTeamId}")
    public ResponseEntity<Void> deleteTechTeam(@PathVariable int techTeamId) {
        if (techTeamRepository.existsById(techTeamId)) {
            techTeamRepository.deleteById(techTeamId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}



