package com.example.music_validate.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MusicDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên bài hát không được để trống")
    @Size(max = 800, message = "Tên bài hát không được vượt quá 800 ký tự")
    @Pattern(
            regexp = "^[^@;,.=\\-+!#$%^&*(){}\\[\\]:\"'<>/?\\\\|`~]+$",
            message = "Tên bài hát không được chứa ký tự đặc biệt như @ ; , . = - + ..."
    )
    private String name;

    @NotBlank(message = "Nghệ sĩ không được để trống")
    @Size(max = 300, message = "Tên nghệ sĩ không được vượt quá 300 ký tự")
    @Pattern(
            regexp = "^[^@;,.=\\-+!#$%^&*(){}\\[\\]:\"'<>/?\\\\|`~]+$",
            message = "Nghệ sĩ không được chứa ký tự đặc biệt như @ ; , . = - + ..."
    )
    private String artist;

    @NotBlank(message = "Thể loại không được để trống")
    @Size(max = 1000, message = "Thể loại không được vượt quá 1000 ký tự")
    @Pattern(
            regexp = "^[^@;.=\\-+!#$%^&*(){}\\[\\]:\"'<>/?\\\\|`~]*$",
            message = "Thể loại chỉ được chứa chữ, số và dấu phẩy (,)"
    )
    private String type;
}
