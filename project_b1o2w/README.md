# My Personal Project

## Monthly Budgeting app - UniSave

This application, named **UniSave**, will serve as a personal budgeting app *for 
university students*. I am interested in this topic since I am 
a university student who struggles with budgeting and saving 
my money. I think a lot of university students like myself 
could benefit from an app that would help plan out daily, 
weekly, and monthly spending. 

*List of features*:
- Pie chart showing what categories your money is being spent on
- How much money is left to spend in each category
- Tips on how to increase savings 

## User Stories 

*Phase 1*:
- As a user, I want to be able to add recent purchases to their designated spending category/categories
- As a user, I want to be able to see how much of my total budget I have spent  
- As a user, I want to be able to see the list of purchases I made in a category after adding a purchase 
- As a user, I want to be able to be able to set up a user profile with my name and my budget 

*Phase 2*:
- As a user, I want to be able to be able to save my purchases added to categories to file
- As a user, I want to be able to load my purchases added to categories from file 

## Instructions for Grader
- Open the application by running the CategoriesWindow class
- When opening the application first click the user button and add a name and income and then press enter
- You can generate the first required event by typing the name of a purchase in the first text field, a cost in the second text field, and selecting a category from the combo box, then clicking on the second text field and pressing the enter key on your keyboard. This adds a purchase to the selected category.
- You can generate the second required event by clicking the calculate button. This shows the total amount spent on purchases. 
- You can locate my visual component on the bottom right of the CategoriesWindow
- You can save the state of my application by clicking the save button on the CategoriesWindow
- You can reload the state of my application by clicking the load button on the CategoriesWindow and then the calculate button.

## Phase 4: Task 2
- Tue Aug 09 16:16:22 PDT 2022
- Calculated total cost of purchases added to list
- Tue Aug 09 16:16:30 PDT 2022
- Burger was added to food Category
- Tue Aug 09 16:16:41 PDT 2022
- Bus Fare was added to transport Category
- Tue Aug 09 16:16:52 PDT 2022
- Ball was added to fun Category
- Tue Aug 09 16:16:54 PDT 2022
- Calculated total cost of purchases added to list

## Phase 4: Task 3
- I would create an abstract class that would be extended by the Category and Graph class. This would help remove duplicate
code between the two classes since duplicate methods could be written in the abstract class, while methods that are specific to each class could be written separately.
- I would also implement Linked lists instead of array lists for the lists that I created in the Graph and category class. 
This would help improve the speed that elements would be added or removed from the list. 
- I would make the CategoriesWindow class have higher cohesion. I would do this by making the CategoriesWindow into multiple classes
that would be specific for buttons, labels, and saving/loading. Having higher cohesion would allow for easier maintenance and make the code 
easier to understand. 
- I would refactor the code to have exception handling when typing in non integer values in the cost and income textfields. This would 
improve the durability of the code by preventing compiling errors from occurring. 
- Create more helper methods in the CategoriesWindow class to reduce duplicate code. An example of this would be for the 
addPurchase method that I have for every category. I would instead replace this with one helper method that could be used for each category. 
