# BasisTask

Library I used for Swipeable Cards : https://github.com/wenchaojiang/AndroidSwipeableCardStack
Library I used for API communication : Retrofit

Using retrofit's GET method the app can retrieve the JSON uploaded on gist. Initially I created a POJO class to get the JSON but
the problem I was facing was the "/" character on Line 1. It made the JSON invalid and retrofit was directly rejecting the response.

I solved this problem by getting the response in String. After that I edited the string and deleted the "/" character.
Then I converted String to JSONObject and then JSONArray and got the value for every key of "text" and passed them to adapter class.

App also has features of undo and reset. CLicking on undo user can go back to the previous card and on clicking the reset button it will reset the card stack.
