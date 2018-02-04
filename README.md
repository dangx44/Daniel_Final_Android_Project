# Daniel_Final_Android_Project

The Main Activity of the app has 2 buttons on the top part of the screen.

In the middle of the screen is a black circle that you click on to open or close a menu with 5 options. 
One for Bread, Vegetables, Fruit, Meat, and Milk.
When clicked you are brought to a listview showing pictures and names of those items with checkboxes.
You can select whatever items you want and add them to the Final List Database by clicking on the Floating Action Button in the bottom
right corner and it will take you back to the Main page where you can continue to click on other menus and a Toast will be displayed. 
If you do not choose any items than the Floating Action Button will not do anything.

The New List button on the left deletes all entries stored in the SQL Database and shows a toast message saying that the list was deleted.

The Final List button on the right takes you to the final page of the app with all the entries that were chosen from the previous menus
stored in a listview. If the Database is empty, then a Toast Message will appear saying the list is empty and remain in the Main Activity.
In the Final list page you are able to click on any of the entries in the listview after which an alert dialog will pop up asking you if 
you want to delete that item or not.  If yes, then the item will be deleted from the listview but still be stored in the Database and a
Toast Message will be displayed.
If you choose no, then the item will not be deleted and displays a Toast Message.

The Final List page has a button on the bottom right corner that will send the list in an email to whomever you choose.
After the email is sent you will be taken back to the Main Activity.
