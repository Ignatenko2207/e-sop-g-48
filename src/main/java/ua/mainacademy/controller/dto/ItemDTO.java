package ua.mainacademy.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private String id;
    private String itemCode;
    private String name;
    private String price;
    private String initPrice;

}
