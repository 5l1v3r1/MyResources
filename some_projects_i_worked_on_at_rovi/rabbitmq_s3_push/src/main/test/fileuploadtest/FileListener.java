package fileuploadtest;

import java.util.HashMap;
import java.util.Map;

import com.rovicorp.rabbitmq_s3_push.interfaces.Handler;
import com.rovicorp.rabbitmq_s3_push.interfaces.ImageListener;

public class FileListener implements ImageListener {

	public void setHandler(Handler handle) {
		byte[] body = "ByteByeByte".getBytes();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("key", "Key");
		
		handle.callback(map, body);
	}

}
