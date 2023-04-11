 * Database system for the accounts and game.
 * We use the Json format to parse data


 * To not overcomplicate the project we made a few assumptions:
 * We don't use database software like MySQL
 * We assume that there is only one computer that is able to play the game which is where all the data is stored
 * We assume that there is not going to be a lot of users to the point of overstoring the Hashmap

 * We don't mention in the design but the age has to be modifiable
 * There can only be one world per child account
 * Only children can play the game

 * There is no Mother or Father only Parent
 * Parent and child relationship is One-to-many not many-to-many (like some accidents in the real world)