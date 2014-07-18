import org.vertx.java.core.Handler;
import org.vertx.java.core.net.NetSocket;
import org.vertx.java.core.streams.Pump;
import org.vertx.java.platform.Verticle;
import org.vertx.java.core.eventbus.*;
import org.vertx.java.core.json.JsonObject;
import java.util.Map;

public class Backend extends Verticle {

  public void start() {
	EventBus eb = vertx.eventBus();
	System.out.println("Backend started");
	
	
	Handler<Message<JsonObject>> myHandler = new Handler<Message<JsonObject>>() {
		public void handle(Message<JsonObject> message) {
			JsonObject map = (JsonObject) message.body().getValue("uri");
			System.out.println("I received a message from " + map.getString("address"));
			JsonObject reply = new JsonObject().putString("message", "This is Java and your address is " + map.getString("address"));
			message.reply(reply);
		}
	};

	eb.registerHandler("my.address", myHandler);
  }
}