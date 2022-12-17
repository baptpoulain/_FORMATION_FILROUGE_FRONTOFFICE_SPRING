package com.example.FilRougeFrontOffice.repository.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "permissions", schema = "bdd_fil_rouge")
public class PermissionsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "permission_id")
    private int permissionId;
    @Basic
    @Column(name = "permission_name")
    private String permissionName;
    @OneToMany(mappedBy = "permissionsByPermissionId")
    private Collection<InteractEntity> interactsByPermissionId;

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermissionsEntity that = (PermissionsEntity) o;

        if (permissionId != that.permissionId) return false;
        if (permissionName != null ? !permissionName.equals(that.permissionName) : that.permissionName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = permissionId;
        result = 31 * result + (permissionName != null ? permissionName.hashCode() : 0);
        return result;
    }

    public Collection<InteractEntity> getInteractsByPermissionId() {
        return interactsByPermissionId;
    }

    public void setInteractsByPermissionId(Collection<InteractEntity> interactsByPermissionId) {
        this.interactsByPermissionId = interactsByPermissionId;
    }
}

