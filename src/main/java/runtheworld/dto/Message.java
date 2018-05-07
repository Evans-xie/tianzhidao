package runtheworld.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务器响应结果的数据封装
 * 如果成功返回(true，相应数据),如果失败返回(false,错误信息)
 *
 * @author evans 2018/2/27 23:44
 */

public class Message {
	/**
	 * 业务代码
	 */
	private int code;
	/**
	 * 描述，状态信息
	 */
	private String desc;
	/**
	 * 数据
	 */
	private Map<String, Object> data =new HashMap<>();

	public Message(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Message(int code, Map<String, Object> data) {
		this.code = code;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public Message putData(String key,Object value){
		this.data.put(key,value);
		return this;
	}

	@Override
	public String toString() {
		return "Message{" +
				"code=" + code +
				", desc='" + desc + '\'' +
				", data=" + data +
				'}';
	}
}

