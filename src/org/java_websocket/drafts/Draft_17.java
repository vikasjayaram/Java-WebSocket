package org.java_websocket.drafts;

import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;

public class Draft_17 extends Draft_10 {
	private String subProtocol;
	private String origin;
	
	public Draft_17() {
		this.subProtocol = null;
		this.origin = null;
	}
	public Draft_17(String subProtocol, String origin) {
		this.subProtocol = subProtocol;
		this.origin = origin;
	}
	@Override
	public HandshakeState acceptHandshakeAsServer( ClientHandshake handshakedata ) throws InvalidHandshakeException {
		int v = readVersion( handshakedata );
		if( v == 13 )
			return HandshakeState.MATCHED;
		return HandshakeState.NOT_MATCHED;
	}

	@Override
	public ClientHandshakeBuilder postProcessHandshakeRequestAsClient( ClientHandshakeBuilder request ) {
		super.postProcessHandshakeRequestAsClient( request );
		if(subProtocol != null) {
			request.put("Sec-WebSocket-Protocol", subProtocol);			
		}
		if(origin != null) {
			request.put("Origin", origin);			
		}
		request.put( "Sec-WebSocket-Version", "13" );// overwriting the previous
		return request;
	}

}
