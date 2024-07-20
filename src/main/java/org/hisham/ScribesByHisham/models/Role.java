package org.hisham.ScribesByHisham.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document(collection = "roles")
public class Role {
    @Id
    private String id;

    @NotBlank
    private RoleName name;
}

