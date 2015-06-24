package org.fox.common.util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;

/**
 * @author jie.huang
 *         Date: 2015/5/14
 *         Time: 11:10
 */
public class KryoUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(KryoUtil.class);

    public static byte[] serialize(Object object) {
        //TODO use object pool for kryo
        Kryo kryo = new Kryo();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (Output output = new Output(bos)) {
            kryo.writeClassAndObject(output, object);
            output.flush();
            return bos.toByteArray();
        }
    }

    public static Object deserialize(byte[] in) {
        if (in == null || in.length <= 0) {
            return null;
        }
        Kryo kryo = new Kryo();
        try (Input input = new Input(in)) {
            return kryo.readClassAndObject(input);
        } catch (KryoException e) {
            LOGGER.warn("kryo deserialize error: ", e);
            return null;
        }
    }

    public static <T> T deserialize(byte[] in, Class<T> clazz) {
        if (in == null || in.length <= 0) {
            return null;
        }
        Kryo kryo = new Kryo();
        try (Input input = new Input(in)) {
            return kryo.readObjectOrNull(input, clazz);
        } catch (KryoException e) {
            LOGGER.warn("kryo deserialize{} error: ", clazz, e);
            return null;
        }
    }

}
