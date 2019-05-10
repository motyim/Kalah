package com.backbase.kalah.dto;

import lombok.Data;

@Data
public class GamePlayResponse {
    int id;
    String uri;
    // TODO: 5/10/2019 search convert array to key value
    int [] status;
}
