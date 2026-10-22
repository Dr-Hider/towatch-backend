package com.example.towatch.api.type.service;

import com.example.towatch.api.type.entity.Type;
import com.example.towatch.api.type.repository.TypeRepository;
import com.example.towatch.api.type.dto.TypeRequest;
import com.example.towatch.api.type.dto.TypeResponse;
import com.example.towatch.api.type.exception.TypeAlreadyExistsException;
import com.example.towatch.api.type.exception.TypeNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<TypeResponse> getAllTypes() {
        return typeRepository.findAll()
                .stream().map(type -> new TypeResponse(
                        type.getId(),
                        type.getName()
                )).toList();
    }

    public TypeResponse getTypeById(Long id) {
        Type type = typeRepository.findById(id)
                .orElseThrow(() -> new TypeNotFoundException(id));

        return new TypeResponse(type.getId(), type.getName());
    }

    @Transactional
    public TypeResponse createType(TypeRequest request) {
        String typeName = request.name().strip();

        if (typeRepository.existsByName(typeName)) {
            throw new TypeAlreadyExistsException(typeName);
        }

        Type type = new Type();
        type.setName(typeName);

        Type newType = typeRepository.save(type);

        return new TypeResponse(newType.getId(), newType.getName());
    }

    @Transactional
    public TypeResponse updateType(Long id, TypeRequest request) {

        Type type = typeRepository.findById(id)
                .orElseThrow(() -> new TypeNotFoundException(id));

        type.setName(request.name().strip());

        Type updatedType = typeRepository.save(type);

        return new TypeResponse(updatedType.getId(), updatedType.getName());
    }

    @Transactional
    public void deleteTypeById(Long id) {
        if (!typeRepository.existsById(id)) {
            throw new TypeNotFoundException(id);
        }

        typeRepository.deleteById(id);
    }
}
