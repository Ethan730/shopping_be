package com.ethan.shopping.dto.address;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class DeleteAddressForm {
    @NotNull
    private Integer id;
}
