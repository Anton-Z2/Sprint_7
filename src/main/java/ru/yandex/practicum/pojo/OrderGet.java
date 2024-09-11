package ru.yandex.practicum.pojo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderGet {

    private Integer courierId;
    private String nearestStation;
    private Integer limit;
    private Integer page;

}
