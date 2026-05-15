package com.example.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionRequest {

    @NotBlank(message = "收藏类型不能为空")
    private String collectionType;

    @NotNull(message = "目标ID不能为空")
    private Long targetId;

}