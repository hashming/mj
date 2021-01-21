package work.mj.com.mj.pojo;

import lombok.Data;

/**
 * 保存登录信息
 */
@Data
public class Register {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
