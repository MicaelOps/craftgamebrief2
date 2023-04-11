Some lack of info from the design that had to be addressed:
    * Parent and child relationship is One-to-many not many-to-many (like some accidents in the real world)
    * We don't use database software like MySQL and use plain text file to store data

 Deviations from the main plan:

    * Homepage for children and parents are together because what is different about them is
      the password and pin which can be manipulated very easily

    * The STEM progress is what enables the child to gain more access to items and harder questions
      and not levels due to levels and STEM progression bar having no correlation.

    * ParentGameMenu has option to view his children or all children progress since levels do not exist

    * There is a one more menu called WorldGameMenu which is just an extension
      of the ChildGameMenu which is the "Continue Game" in the design

    * Confusion between naming of World and Game hence we used World ("Start New World","Continue World")

Data is saved when the user chooses the exit option.

Important Classes to read:

    Account.class ->
        ParentAccount.class -> Explains how data is handled
        ChildAccount.class -> Explains how data is handled

    Menu.class -> Explain the Menu system
    OptionsMenu.class -> Explain how the user navigates

Extra classes to read at last:
    Database.class -> Explains how data is loaded and stored
    Item.class, ItemType.class -> Class of items
    Questions.class -> Question bank
    MethodUtils -> Utilities

Flow of the program:

    Main.main()
         -> Starts the program by loading data Database.loadData()
         -> Initializes the first menu, HomepageMenu.class
         -> Starts the Main Menu loop

    HomepageMenu.class
        -> Chooses whether the user is a child and parent
        -> Move to AuthMenu.class

    AuthMenu.class
        -> Either registers or Logs in as a parent/child
        -> Move to ParentGameMenu.class or ChildGameMenu.class
        -> Exit

    ParentGameMenu.class
        -> Change Child PIN -> Select Child
        -> View Children STEM progression
        -> View all Children STEM progression
        -> Exit

    ChildGameMenu.class
        -> Start New World
        -> Continue World (WorldGameMenu.class)
        -> View Leaderboard
        -> Exit

    WorldGameMenu.class
        -> Save Game
        -> View Inventory
        -> Explore the world
        -> Craft Items
        -> Exit