# spotty-auth-server

Small HTTP server to handle Spotify user authentication for API clients.

For more information see https://developer.spotify.com/documentation/general/guides/authorization-guide/#authorization-flows

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server
	
## Building

To get an executable server JAR, run:

	lein ring uberjar

A Docker container is published to joelgluth/spotty-auth-server from the Dockerfile in the root directory. To build locally, first build the uberjar and then

	docker build .

## Usage

1. Make a call to https://accounts.spotify.com/authorize and pass some
   unique-ish $thing as its state parameter, and
   https://spotty-auth-server.example.com/authorized as the redirect parameter (see Spotify docs)
2. (Spotify will redirect to its auth page for user input)
3. Make a call to the server's /token/$thing, which will block
4. Spotify will call the server's authorized endpoint with the auth token
5. The all to /token endpoint will unblock and return the token

## License

Copyright © 2019 FIXME
