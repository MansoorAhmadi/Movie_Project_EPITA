package fr.epita.movies.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link fr.epita.movies.domain.MovieUser} entity.
 */
public class MovieUserDTO implements Serializable {
    
    private Long id;

    private String username;

    private Set<RoleDTO> roles = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MovieUserDTO movieUserDTO = (MovieUserDTO) o;
        if (movieUserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), movieUserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MovieUserDTO{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", roles='" + getRoles() + "'" +
            "}";
    }
}
