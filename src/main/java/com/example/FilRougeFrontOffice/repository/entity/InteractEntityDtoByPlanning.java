package com.example.FilRougeFrontOffice.repository.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link InteractEntity} entity
 */
@Data
public class InteractEntityDtoByPlanning implements Serializable {
    private final UsersEntityDto usersByUserId;
    private final PermissionsEntityDto permissionsByPermissionId;

    /**
     * A DTO for the {@link UsersEntity} entity
     */
    @Data
    public static class UsersEntityDto implements Serializable {
        private final int userId;
        private final String userName;
        private final String userFirstname;
        private final String userPicture;
    }

    /**
     * A DTO for the {@link PermissionsEntity} entity
     */
    @Data
    public static class PermissionsEntityDto implements Serializable {
        private final int permissionId;
        private final String permissionName;
    }

    public static InteractEntityDtoByPlanning from (InteractEntity interactEntity){
        InteractEntityDtoByPlanning.UsersEntityDto usersEntityDto = new UsersEntityDto(interactEntity.getUsersByUserId().getUserId(), interactEntity.getUsersByUserId().getUserName(),interactEntity.getUsersByUserId().getUserFirstname(), interactEntity.getUsersByUserId().getUserPicture());

        InteractEntityDtoByPlanning.PermissionsEntityDto permissionsEntityDto = new InteractEntityDtoByPlanning.PermissionsEntityDto(interactEntity.getPermissionsByPermissionId().getPermissionId(), interactEntity.getPermissionsByPermissionId().getPermissionName());

        return new InteractEntityDtoByPlanning(usersEntityDto, permissionsEntityDto);
    }

}