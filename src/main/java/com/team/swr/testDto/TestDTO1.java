package com.team.swr.testDto;

import com.team.swr.model.testModel.Test1Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestDTO1 {

    private String data2;

    private String data3;

    public TestDTO1(final Test1Entity entity) {
        this.data2 = entity.getData2();
        this.data3 = entity.getData3();
    }

    public static Test1Entity toEntity(final TestDTO1 dto) {
        return Test1Entity.builder()
                .data2(dto.getData2())
                .data3(dto.getData3())
                .build();
    }

}
