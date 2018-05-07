package runtheworld.util;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

/**
 * 对象实现implement serializable，jdk内部序列化机制--->性能低、占用空间大、简单
 * 采用自定义序列化
 * protostuff:pojo
 *
 * @author evans 2018/1/18 0:37
 */

public class SerializeUtil {

    public static <T> byte[] serialize(T obj) {
        try {
            Class<T> clazz = (Class<T>) obj.getClass();
            Schema<T> schema = RuntimeSchema.createFrom(clazz);
            return ProtostuffIOUtil.toByteArray(obj, schema,
                    LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static <T> T deserialize(byte[] data, Class<T> clazz) {
        Schema<T> schema = null;
        try {
            schema = RuntimeSchema.createFrom(clazz);
            //空对象
            T obj = schema.newMessage();
            //反序列化:通过schema把data赋值到空对象obj中
            ProtostuffIOUtil.mergeFrom(data, obj, schema);
            return obj;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
