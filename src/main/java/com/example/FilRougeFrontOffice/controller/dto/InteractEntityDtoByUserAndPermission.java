package com.example.FilRougeFrontOffice.controller.dto;

import com.example.FilRougeFrontOffice.repository.entity.InteractEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class InteractEntityDtoByUserAndPermission implements Serializable {
    private final PermissionsEntityDto permissionsByPermissionId;

    @Data
    public static class PermissionsEntityDto implements Serializable {
        private final int permissionId;
        private final String permissionName;
    }

    public static InteractEntityDtoByUserAndPermission from (InteractEntity interactEntity){

        InteractEntityDtoByUserAndPermission.PermissionsEntityDto permissionsEntityDto = new InteractEntityDtoByUserAndPermission.PermissionsEntityDto(interactEntity.getPermissionsByPermissionId().getPermissionId(), interactEntity.getPermissionsByPermissionId().getPermissionName());

          return new InteractEntityDtoByUserAndPermission(permissionsEntityDto);

    }
}
