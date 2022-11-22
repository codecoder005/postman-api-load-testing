package com.orgofarmsgroup.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchFilters implements Serializable {
    @Serial
    private static final long serialVersionUID = 101L;

    @Pattern(regexp = "[a-zA-Z]{3,20}")
    private String name;

    @Min(value = 1L)
    @Max(value = 10000L)
    private Long count;

    @Pattern(regexp = "(asc)|(desc)", message = "invalid sorting option")
    private String sortBy = "asc";
}
