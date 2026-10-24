package com.example.towatch.api.type.service;

import com.example.towatch.api.type.entity.Type;
import com.example.towatch.api.type.repository.TypeRepository;
import com.example.towatch.api.type.dto.TypeRequest;
import com.example.towatch.api.type.dto.TypeResponse;
import com.example.towatch.api.type.exception.TypeAlreadyExistsException;
import com.example.towatch.api.type.exception.TypeNotFoundException;
import com.example.towatch.api.user.exception.UserNotFoundException;
import com.example.towatch.api.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    private final TypeRepository typeRepository;
    private final UserRepository userRepository;

    public TypeService(
            TypeRepository typeRepository,
            UserRepository userRepository
    ) {
        this.typeRepository = typeRepository;
        this.userRepository = userRepository;
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
                .orElseThrow(() -> new TypeNotFoundException());

        return new TypeResponse(type.getId(), type.getName());
    }

    @Transactional
    public TypeResponse createType(TypeRequest request, Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }

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
    public TypeResponse updateType(Long id, TypeRequest request, Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }

        Type type = typeRepository.findById(id)
                .orElseThrow(() -> new TypeNotFoundException());

        type.setName(request.name().strip());

        Type updatedType = typeRepository.save(type);

        return new TypeResponse(updatedType.getId(), updatedType.getName());
    }

    @Transactional
    public void deleteTypeById(Long id, Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }

        if (!typeRepository.existsById(id)) {
            throw new TypeNotFoundException();
        }

        typeRepository.deleteById(id);
    }
}
