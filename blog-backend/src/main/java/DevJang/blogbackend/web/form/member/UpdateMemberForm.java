package DevJang.blogbackend.web.form.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateMemberForm {
    @NotNull
    private Long id;

    @NotBlank
    private String Email;

    @NotNull
    @Size(min = 8, max = 20)
    private String password;

    @NotBlank
    @Size(min = 2, max = 10)
    private String nickName;
}
