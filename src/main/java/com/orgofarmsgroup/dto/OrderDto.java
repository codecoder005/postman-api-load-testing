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
public class OrderDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -98764667710L;

    private Long oid;
}
