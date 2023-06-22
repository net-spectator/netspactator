package org.net.webcoreservice.service;

import entities.devices.ClientHardwareInfo;
import lombok.RequiredArgsConstructor;
import org.net.webcoreservice.dto.TrackedEquipmentDto;
import org.net.webcoreservice.entities.TrackedEquipment;
import org.net.webcoreservice.repository.TrackedEquipmentRepository;
import org.springframework.stereotype.Service;
import services.ClientListenersDataBus;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrackedEquipmentService {

    private final TrackedEquipmentRepository trackedEquipmentRepository;

    public Optional<TrackedEquipment> findById(Long id) {
        return trackedEquipmentRepository.findById(id);
    }

    public List<TrackedEquipment> findAll() {
        return trackedEquipmentRepository.findAll();
    }

    public void deleteById(Long id) {
        trackedEquipmentRepository.deleteById(id);
    }

    public List<TrackedEquipment> showBlackList() {
        return trackedEquipmentRepository.showBlackList();
    }

    public void addToBlackList(Long id) {
        blackListOperation(id, 1);
        disconnect(id);
    }

    public void removeFromBlackList(Long id) {
        blackListOperation(id, 0);
    }

    public void disconnect(Long id) {
        ClientListenersDataBus.disconnectClient(id);
    }

    public ClientHardwareInfo getEquipmentHardwareInfo(Long id) {
        return ClientListenersDataBus.getClientHardwareInfo(id);
    }

    public void createNewTrackedEquipment(TrackedEquipmentDto trackedEquipmentDto) {
        TrackedEquipment trackedEquipment = new TrackedEquipment();
        trackedEquipment.setEquipmentUuid(trackedEquipmentDto.getUuid());
        trackedEquipment.setEquipmentTitle(trackedEquipmentDto.getTitle());
        trackedEquipment.setEquipmentIpAddress(trackedEquipmentDto.getIp());
        trackedEquipment.setEquipmentOnlineStatus(trackedEquipmentDto.getOnlineStatus());
        trackedEquipment.setEquipmentMacAddress(trackedEquipmentDto.getMac());
        trackedEquipmentRepository.save(trackedEquipment);
    }

    public void blackListOperation(Long id, int blackListStatus) {
        TrackedEquipment trackedEquipment = findById(id).get();
        trackedEquipment.setBlackList(blackListStatus);
        trackedEquipmentRepository.save(trackedEquipment);
    }

    public boolean isExist(Long id) {
        Optional<TrackedEquipment> entity = findById(id);
        if (!entity.isPresent()) {
            return false;
        }
        return true;
    }
}
