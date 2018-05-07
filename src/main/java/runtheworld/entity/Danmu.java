package runtheworld.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 弹幕实体类
 *
 * @author evans 2018/5/6 14:15
 */

public class Danmu implements Serializable {

	private static final long serialVersionUID = -7898194272883238670L;

	private long id;
	/**
	 * 发送者id，先以sessionId作为id
	 */
	private String userId;

	/**
	 * 场景id
	 */
	private int viewId;

	/**
	 * 弹幕内容
	 */
	private String contents;

	/**
	 * 点赞次数
	 */
	private int zan;
	/**
	 * 发布时间
	 */
	private Date time;

	/**
	 * 颜色:rgb
	 */
	private String color;

	/**
	 * 对应的图片坐标
	 */
	private double x;

	private double y;

	public Danmu() {
	}

	public Danmu(String userId, int viewId, String contents, Date time, String color) {
		this.userId = userId;
		this.viewId = viewId;
		this.contents = contents;
		this.time = time;
		this.color = color;
	}

	public Danmu(String userId, int viewId, String contents, Date time) {
		this.userId = userId;
		this.viewId = viewId;
		this.contents = contents;
		this.time = time;
		this.color = "#FFFFFF";
	}

	public Danmu(String userId, int viewId, String contents, Date time, String color, double x, double y) {
		this.userId = userId;
		this.viewId = viewId;
		this.contents = contents;
		this.time = time;
		this.color = color;
		this.x = x;
		this.y = y;
	}

	public int getZan() {
		return zan;
	}

	public void setZan(int zan) {
		this.zan = zan;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getViewId() {
		return viewId;
	}

	public void setViewId(int viewId) {
		this.viewId = viewId;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Danmu{" +
				"id=" + id +
				", userId='" + userId + '\'' +
				", viewId=" + viewId +
				", contents='" + contents + '\'' +
				", time=" + time +
				", color='" + color + '\'' +
				", x=" + x +
				", y=" + y +
				'}';
	}
}
