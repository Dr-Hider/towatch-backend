package com.example.towatch.api.status.service;

import com.example.towatch.api.status.dto.StatusRequest;
import com.example.towatch.api.status.dto.StatusResponse;
import com.example.towatch.api.status.entity.Status;
import com.example.towatch.api.status.exception.StatusAlreadyExistsException;
import com.example.towatch.api.status.exception.StatusNotFoundException;
import com.example.towatch.api.status.repository.StatusRepository;
import com.example.towatch.api.user.exception.UserNotFoundException;
import com.example.towatch.api.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;

    public StatusService(
            StatusRepository statusRepository,
            UserRepository userRepository
    ) {
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
    }

    public List<StatusResponse> getAllStatuses() {
        return statusRepository.findAll()
                .stream().map(status -> new StatusResponse(
                        status.getId(),
                        status.getName()
                )).toList();
    }

    public StatusResponse getStatusById(Long id) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new StatusNotFoundException());

        return new StatusResponse(status.getId(), status.getName());
    }

    @Transactional
    public StatusResponse createStatus(StatusRequest request, Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }

        String statusName = request.name().strip();

        if (statusRepository.existsByNameIgnoreCase(statusName)) {
            throw new StatusAlreadyExistsException(statusName);
        }

        Status status = new Status();
        status.setName(statusName);

        Status newStatus = statusRepository.save(status);

        return new StatusResponse(newStatus.getId(), newStatus.getName());
    }

    @Transactional
    public StatusResponse updateStatus(Long id, StatusRequest request, Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }

        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new StatusNotFoundException());

        status.setName(request.name().strip());

        Status updatedStatus = statusRepository.save(status);

        return new StatusResponse(updatedStatus.getId(), updatedStatus.getName());
    }

    @Transactional
    public void deleteStatus(Long id, Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }

        if (!statusRepository.existsById(id)) {
            throw new StatusNotFoundException();
        }

        statusRepository.deleteById(id);
    }
}
