package ru.larionov.inventoryservice.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class TypeMaterialDTO {

    private UUID id;
    private String name;
    private String description;
}
