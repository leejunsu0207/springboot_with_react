package com.team.swr;

import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Builder
@RequiredArgsConstructor
public class DemoModel {

    @NonNull
    private String id;
}
