package com.orgofarmsgroup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -754667710L;

    private Long uid;
    private String name;
    private String email;
}
