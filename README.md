# Planes-TCP

Simple airplane shooter game based on TCP client-server model implemented in Java

How it works:

- Once the server is up and running, the client has to connect with their username (server finds it in "settings.properties")
- If the username is valid and the client begins the game, the server will randomize (one of the three configurations found in text files) the airplanes model
- Client has to take all airplanes down, shooting one by one, for values of the 10x10 matrix, introducing the line and column 
- A, B, C represent the front of the airplanes, similar triple digits represent the back, 5 similar digits are for the wings and one digit is for the rest of the airplane structure (for instance A, 111, 11111, 1), while the 0s are blank space
- Server will respond after each shot as it follows: 0 if it was a miss, 1 if a part of the airplanes was shot, or X if one of the airplanes was taken down
- Once the airplanes were taken down, the client has the option to disconnect or replay the game
