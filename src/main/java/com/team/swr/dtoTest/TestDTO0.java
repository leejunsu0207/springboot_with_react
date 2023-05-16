package com.team.swr.dtoTest;

import com.team.swr.model.Test0Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestDTO0 {

    private String data0;

    private String data1;

    public TestDTO0(final Test0Entity entity) {
        this.data0 = entity.getData0();
        this.data1 = entity.getData1();
    }

    public static Test0Entity toEntity(final TestDTO0 dto){
        return Test0Entity.builder()
                .data0(dto.getData0())
                .data1(dto.getData1())
                .build();
    }

}
