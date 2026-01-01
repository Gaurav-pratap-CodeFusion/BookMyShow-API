package com.gpcf.BookMyShow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "UserDto",
        description = "DTO for transferring User data between client and server"
)
public class UserDto {

    @NotNull
    @Schema(
            description = "Unique user identifier",
            example = "101",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @NotBlank
    @Schema(
            description = "Full name of the user",
            example = "Gaurav Pratap"
    )
    private String name;

    @Email
    @NotBlank
    @Schema(
            description = "Email address of the user",
            example = "gaurav@gmail.com"
    )
    private String email;

    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid mobile number"
    )
    @Schema(
            description = "10 digit Indian mobile number",
            example = "9876543210"
    )
    private String phoneNumber;
}
