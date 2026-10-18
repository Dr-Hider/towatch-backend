package com.example.towatch.type;

import com.example.towatch.type.dto.CreateTypeRequest;
import com.example.towatch.type.dto.TypeResponse;
import com.example.towatch.type.dto.UpdateTypeRequest;
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
    public List<TypeResponse> getAllTypes() {
        return typeService.getAllTypes();
    }

    @GetMapping("/{id}")
    public TypeResponse getTypeById(@PathVariable Long id) {
        return typeService.getTypeById(id);
    }

    @PostMapping
    ResponseEntity<TypeResponse> createType(
            @Valid @RequestBody CreateTypeRequest request) {

        TypeResponse response = typeService.createType(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}")
    ResponseEntity<TypeResponse> updateType(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTypeRequest request) {

        TypeResponse response = typeService.updateType(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<TypeResponse> deleteType(
            @Valid @RequestBody Long id) {

        typeService.deleteTypeById(id);

        return ResponseEntity.noContent().build();
    }
}
