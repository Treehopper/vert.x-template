var vertx = require('vertx')
//var console = require('vertx/console');
var container = require('vertx/container')

container.deployModule('eu.hohenegger~java-example-app~1.0');


var eb = vertx.eventBus

handler = function(req) {
  
	var request = {
		uri: req.remoteAddress()
	}
  
	eb.send('my.address', request, function(response) {
		req.response.headers['Content-Type'] = "text/plain";
		req.response.end(response.message);
	});
	
	//req.response.end("Hello World!");
}

vertx.createHttpServer().requestHandler(handler).listen(8080, 'localhost');
