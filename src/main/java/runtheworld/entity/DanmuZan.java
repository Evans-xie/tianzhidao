package runtheworld.entity;

import java.util.Date;

public class DanmuZan {
    public DanmuZan() {
    }

    public DanmuZan(Long danmuId, Long userId, Date gmtCreate, Date gmtModified) {
        this.danmuId = danmuId;
        this.userId = userId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column danmu_zan.id
     *
     * @mbggenerated Tue May 22 12:44:23 GMT+08:00 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column danmu_zan.danmu_id
     *
     * @mbggenerated Tue May 22 12:44:23 GMT+08:00 2018
     */
    private Long danmuId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column danmu_zan.user_id
     *
     * @mbggenerated Tue May 22 12:44:23 GMT+08:00 2018
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column danmu_zan.gmt_create
     *
     * @mbggenerated Tue May 22 12:44:23 GMT+08:00 2018
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column danmu_zan.gmt_modified
     *
     * @mbggenerated Tue May 22 12:44:23 GMT+08:00 2018
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column danmu_zan.is_deleted
     *
     * @mbggenerated Tue May 22 12:44:23 GMT+08:00 2018
     */
    private Byte isDeleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column danmu_zan.id
     *
     * @return the value of danmu_zan.id
     *
     * @mbggenerated Tue May 22 12:44:23 GMT+08:00 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column danmu_zan.id
     *
     * @param id the value for danmu_zan.id
     *
     * @mbggenerated Tue May 22 12:44:23 GMT+08:00 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column danmu_zan.danmu_id
     *
     * @return the value of danmu_zan.danmu_id
     *
     * @mbggenerated Tue May 22 12:44:23 GMT+08:00 2018
     */
    public Long getDanmuId() {
        return danmuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column danmu_zan.danmu_id
     *
     * @param danmuId the value for danmu_zan.danmu_id
     *
     * @mbggenerated Tue May 22 12:44:23 GMT+08:00 2018
     */
    public void setDanmuId(Long danmuId) {
        this.danmuId = danmuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column danmu_zan.user_id
     *
     * @return the value of danmu_zan.user_id
     *
     * @mbggenerated Tue May 22 12:44:23 GMT+08:00 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column danmu_zan.user_id
     *
     * @param userId the value for danmu_zan.user_id
     *
     * @mbggenerated Tue May 22 12:44:23 GMT+08:00 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column danmu_zan.gmt_create
     *
     * @return the value of danmu_zan.gmt_create
     *
     * @mbggenerated Tue May 22 12:44:23 GMT+08:00 2018
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column danmu_zan.gmt_create
     *
     * @param gmtCreate the value for danmu_zan.gmt_create
     *
     * @mbggenerated Tue May 22 12:44:23 GMT+08:00 2018
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column danmu_zan.gmt_modified
     *
     * @return the value of danmu_zan.gmt_modified
     *
     * @mbggenerated Tue May 22 12:44:23 GMT+08:00 2018
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column danmu_zan.gmt_modified
     *
     * @param gmtModified the value for danmu_zan.gmt_modified
     *
     * @mbggenerated Tue May 22 12:44:23 GMT+08:00 2018
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column danmu_zan.is_deleted
     *
     * @return the value of danmu_zan.is_deleted
     *
     * @mbggenerated Tue May 22 12:44:23 GMT+08:00 2018
     */
    public Byte getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column danmu_zan.is_deleted
     *
     * @param isDeleted the value for danmu_zan.is_deleted
     *
     * @mbggenerated Tue May 22 12:44:23 GMT+08:00 2018
     */
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
}