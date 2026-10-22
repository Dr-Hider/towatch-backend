package com.example.towatch.api.type.controller;

import com.example.towatch.api.type.dto.TypeRequest;
import com.example.towatch.api.type.dto.TypeResponse;
import com.example.towatch.api.type.service.TypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")
public class TypeController {
    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public ResponseEntity<List<TypeResponse>> getAllTypes() {
        List<TypeResponse> response = typeService.getAllTypes();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeResponse> getTypeById(@PathVariable Long id) {
        TypeResponse response = typeService.getTypeById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<TypeResponse> createType(
            @Valid @RequestBody TypeRequest request
    ) {
        TypeResponse response = typeService.createType(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeResponse> updateType(
            @PathVariable Long id,
            @Valid @RequestBody TypeRequest request
    ) {
        TypeResponse response = typeService.updateType(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.BodyBuilder deleteType(@PathVariable Long id) {
        typeService.deleteTypeById(id);

        return ResponseEntity.status(HttpStatus.OK);
    }
}
