package com.tousime_alternative.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ExtrasDtos {
    private List<ExtrasDto> extras;
}
