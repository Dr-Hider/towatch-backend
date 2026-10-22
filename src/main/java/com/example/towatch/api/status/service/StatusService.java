package com.example.towatch.api.status.service;

import com.example.towatch.api.status.dto.StatusRequest;
import com.example.towatch.api.status.dto.StatusResponse;
import com.example.towatch.api.status.entity.Status;
import com.example.towatch.api.status.exception.StatusAlreadyExistsException;
import com.example.towatch.api.status.exception.StatusNotFoundException;
import com.example.towatch.api.status.repository.StatusRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<StatusResponse> getAllStatuses() {
        return statusRepository.findAll()
                .stream().map(type -> new StatusResponse(
                        type.getId(),
                        type.getName()
                )).toList();
    }

    public StatusResponse getStatusById(Long id) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new StatusNotFoundException(id));

        return new StatusResponse(status.getId(), status.getName());
    }

    @Transactional
    public StatusResponse createStatus(StatusRequest request) {
        if (statusRepository.existsByName(request.name())) {
            throw new StatusAlreadyExistsException(request.name());
        }

        Status status = new Status();
        status.setName(request.name());

        Status newStatus = statusRepository.save(status);

        return new StatusResponse(newStatus.getId(), newStatus.getName());
    }

    @Transactional
    public StatusResponse updateStatus(Long id, StatusRequest request) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new StatusNotFoundException(id));

        status.setName(request.name());

        Status updatedStatus = statusRepository.save(status);

        return new StatusResponse(updatedStatus.getId(), updatedStatus.getName());
    }

    @Transactional
    public void deleteStatus(Long id) {
        if (!statusRepository.existsById(id)) {
            throw new StatusNotFoundException(id);
        }

        statusRepository.deleteById(id);
    }
}
