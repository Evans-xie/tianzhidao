package runtheworld.entity.UserDemo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author evans 2018/5/24 12:10
 */
@ApiModel
public class UserDanmu {
    @ApiModelProperty(value = "用户id",required = true,example = "1")
    private Long userId;
    @ApiModelProperty(value = "弹幕id",required = true,example = "10")
    private Long danmuId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDanmuId() {
        return danmuId;
    }

    public void setDanmuId(Long danmuId) {
        this.danmuId = danmuId;
    }
}
