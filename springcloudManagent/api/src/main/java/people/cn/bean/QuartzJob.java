package people.cn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : FENGZHI
 * create at:  2020/4/11  下午9:28
 * @description: 定时任务实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuartzJob implements Serializable {

    public static final String JOB_KEY = "JOB_KEY";

    @NotNull(groups = {Update.class})
    private String id;

    /** 定时器名称*/
    @NotBlank
    private String jobName;

    /** Bean名称 */
    @NotBlank
    private String beanName;
    /** 方法名称 */
    @NotBlank
    private String methodName;

    /** 参数 */
    @NotBlank
    private String params;

    /** cron表达式 */
    @NotBlank
    private String cronExpression;

    /** 状态 */
    @NotBlank
    private Boolean pause = false;

    /** 备注 */
    @NotBlank
    private String remark;

    public @interface Update{}

    @Override
    public String toString() {
        return "QuartzJob{" +
                "id='" + id + '\'' +
                ", jobName='" + jobName + '\'' +
                ", beanName='" + beanName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params='" + params + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", pause=" + pause +
                ", remark='" + remark + '\'' +
                '}';
    }
}
