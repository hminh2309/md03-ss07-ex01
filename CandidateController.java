package org.example.session07.controller;

import org.example.session07.dto.CandidateCreateDTO;
import org.example.session07.entity.Candidate;
import org.example.session07.repository.CandidateRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateController(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@Valid @RequestBody CandidateCreateDTO dto) {
        Candidate candidate = new Candidate(
                dto.getFullName(),
                dto.getEmail(),
                dto.getAge(),
                dto.getYearsOfExperience()
        );

        Candidate savedCandidate = candidateRepository.save(candidate);

        return new ResponseEntity<>(savedCandidate, HttpStatus.CREATED);
    }
}