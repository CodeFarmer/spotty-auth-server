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

## License

Copyright © 2019 FIXME
