package com.example.FilRougeFrontOffice.controller.dto;

import com.example.FilRougeFrontOffice.repository.entity.InteractEntity;
import com.example.FilRougeFrontOffice.repository.entity.PermissionsEntity;
import com.example.FilRougeFrontOffice.repository.entity.PlanningsEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link InteractEntity} entity
 */
@Data
public class InteractEntityDto implements Serializable {
    private final PlanningsEntityDto planningsByPlanningId;
    private final PermissionsEntityDto permissionsByPermissionId;

    /**
     * A DTO for the {@link PlanningsEntity} entity
     */
    @Data
    public static class PlanningsEntityDto implements Serializable {
        private final int planningId;
        private final String planningTitle;
        private final int userId;
    }

    /**
     * A DTO for the {@link PermissionsEntity} entity
     */
    @Data
    public static class PermissionsEntityDto implements Serializable {
        private final int permissionId;
        private final String permissionName;
    }

    public static InteractEntityDto from (InteractEntity interactEntity){
        InteractEntityDto.PlanningsEntityDto planningsEntityDto = new InteractEntityDto.PlanningsEntityDto(interactEntity.getPlanningsByPlanningId().getPlanningId(), interactEntity.getPlanningsByPlanningId().getPlanningTitle(),interactEntity.getPlanningsByPlanningId().getUserId());
        InteractEntityDto.PermissionsEntityDto permissionsEntityDto = new InteractEntityDto.PermissionsEntityDto(interactEntity.getPermissionsByPermissionId().getPermissionId(), interactEntity.getPermissionsByPermissionId().getPermissionName());
        return new InteractEntityDto(planningsEntityDto, permissionsEntityDto);
    }

}