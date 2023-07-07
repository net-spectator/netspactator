package org.net.usermanage.controllers;

import lombok.RequiredArgsConstructor;
import org.net.usermanage.services.UserActualizeService;
import org.net.users.converters.UserConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import users.dtos.UserDTO;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserConverter userConverter;

    private final UserActualizeService userActualizeService;

    @GetMapping
    public List<UserDTO> getUsers() {
        return userActualizeService.getUsers();
    }

    @GetMapping("{uuid}")
    public UserDTO getUser(@PathVariable UUID uuid) {
        return userConverter.toDTO(userActualizeService.getUser(uuid).get());
    }
}
